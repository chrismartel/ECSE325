library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
-- entity declaration --
entity g29_FIR is 
port(   x : in std_logic_vector(15 downto 0); 		-- new enqueued input signal
	enable : in std_logic;				-- enable signal for the output computation
	clk : in std_logic; 				-- clock
	rst : in std_logic; 				-- asynchronous reset active high
	y : out std_logic_vector(16 downto 0)		-- output signal	  
	);
end g29_FIR;

-- architecture --
architecture myFIR of g29_FIR is 
		-- signals declaration--
	type array_16 is array(24 downto 0) of signed(15 downto 0);	-- array type of size 25, holding 16 bits elements
	signal placeholders : array_16;			-- array of 25 16-bits place holders for the inputs in queue
	
	-- array of 25 constant 16-bits coefficients
	-- set coefficients in function of converted values obtained from Java code
	constant coefficients : array_16:=("0000001001110010","0000000000010001","1111111111010010","1111111011011101","0000001100011001",
					   "1111110110100110","1111110000001101","0000110110111100","1110110001110010","0000110111110111",
					   "0000001100000111","1110101000001001","0001111000110011","1110101000001001","0000001100000111",
					   "0000110111110111","1110110001110010","0000110110111100","1111110000001101","1111110110100110",
					   "0000001100011001","1111111011011101","1111111111010010","0000000000010001","0000001001110010");			
	
	signal temp : signed(31 downto 0);		-- temporary output signal of 32 bits

	begin
	-- process depending on clock and reset inputs
	firProcess: process(clk,rst)
		begin
			if rst = '1' then 					-- asynchronous active-high reset
				placeholders <= (others=>(others=>'0'));	-- reset all the place holders for the inputs
				temp <= (others=>'0');
			elsif(clk'event and clk = '1' and enable = '1' ) then 	-- on a clock rising edge and when enable is high

					for i in 1 to 24 loop 			-- shift first 24 place holders
						placeholders(i) <= placeholders(i-1); 	-- each place holder takes the value of its predecessor
					end loop;
					placeholders(0) <= signed(x); 		-- the first place holder is assigned to the new enqueued input

					-- compute temporary value for the input
					-- sum of 25 products
					temp <=  (coefficients(0)*signed(x)) + (coefficients(1)*placeholders(0))+ (coefficients(2)*placeholders(1))
					+ (coefficients(3)*placeholders(2))+ (coefficients(4)*placeholders(3))+ (coefficients(5)*placeholders(4))
					+ (coefficients(6)*placeholders(5)) + (coefficients(7)*placeholders(6)) + (coefficients(8)*placeholders(7))
					+ (coefficients(9)*placeholders(8))+ (coefficients(10)*placeholders(9)) + (coefficients(11)*placeholders(10))
					+ (coefficients(12)*placeholders(11)) + (coefficients(13)*placeholders(12)) + (coefficients(14)*placeholders(13))
					+ (coefficients(15)*placeholders(14)) + (coefficients(16)*placeholders(15)) + (coefficients(17)*placeholders(16))
					+ (coefficients(18)*placeholders(17)) + (coefficients(19)*placeholders(18)) + (coefficients(20)*placeholders(19))
					+ (coefficients(21)*placeholders(20)) + (coefficients(22)*placeholders(21)) + (coefficients(23)*placeholders(22))
					+ (coefficients(24)*placeholders(23));
			end if;
	
		end process;
		
		y <= std_logic_vector(temp(31 downto 15));			-- concurently assign the output y, take the 17 bits after the first 15 bits, since the fraction 
										-- is doubled when computing a product.
		
	end myFIR;
