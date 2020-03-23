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
	signal coefficients : array_16;			-- array of 25 16-bits coefficients
	signal temp : signed(32 downto 0);		-- temporary output signal of 33 bits

	begin

	-- set coefficients in function of converted values obtained from Java code

	coefficients(0) <= "0000001001110010";
	coefficients(1) <= "0000000000010001";
	coefficients(2) <= "1111111111010010";
	coefficients(3) <= "1111111011011101";
	coefficients(4) <= "0000001100011001";
	coefficients(5) <= "1111110110100110";
	coefficients(6) <= "1111110000001101";
	coefficients(7) <= "0000110110111100";
	coefficients(8) <= "1110110001110010";
	coefficients(9) <= "0000110111110111";
	coefficients(10) <= "0000001100000111";
	coefficients(11) <= "1110101000001001";
	coefficients(12) <= "0001111000110011";
	coefficients(13) <= "1110101000001001";
	coefficients(14) <= "0000001100000111";
	coefficients(15) <= "0000110111110111";
	coefficients(16) <= "1110110001110010";
	coefficients(17) <= "0000110110111100";
	coefficients(18) <= "1111110000001101";
	coefficients(19) <= "1111110110100110";
	coefficients(20) <= "0000001100011001";
	coefficients(21) <= "1111111011011101";
	coefficients(22) <= "1111111111010010";
	coefficients(23) <= "0000000000010001";
	coefficients(24) <= "0000001001110010";

	-- process depending on clock and reset inputs
	firProcess: process(clk,rst)
		begin
			if rst = '1' then 					-- asynchronous active-high reset
				placeholders <= (others=>(others=>'0'));	-- reset all the place holders for the inputs
	
			elsif(clk'event and clk = '1' ) then 			-- on a clock rising edge
				if enable = '1' then				-- check if enable is high before computing new output
						
					for i in 1 to 24 loop 			-- shift first 24 place holders
						placeholders(i) <= placeholders(i-1); 	-- each place holder takes the value of its predecessor
					end loop;
					placeholders(0) <= signed(x); 		-- the first place holder is assigned to the new enqueued input

					-- compute temporary value for the input
					-- sum of 25 products, each product is extended by 1 bit to avoid overflow when summing
					temp <=  resize((coefficients(0)*signed(x)),33) + resize((coefficients(1)*placeholders(0)),33)+ resize((coefficients(2)*placeholders(1)),33)
						+ resize((coefficients(3)*placeholders(2)),33)+ resize((coefficients(4)*placeholders(3)),33)+ resize((coefficients(5)*placeholders(4)),33)
						+ resize((coefficients(6)*placeholders(5)),33) + resize((coefficients(7)*placeholders(6)),33) + resize((coefficients(8)*placeholders(7)),33)
						+ resize((coefficients(9)*placeholders(8)),33)+ resize((coefficients(10)*placeholders(9)),33) + resize((coefficients(11)*placeholders(10)),33)
						+ resize((coefficients(12)*placeholders(11)),33) + resize((coefficients(13)*placeholders(12)),33) + resize((coefficients(14)*placeholders(13)),33)
						+ resize((coefficients(15)*placeholders(14)),33) + resize((coefficients(16)*placeholders(15)),33) + resize((coefficients(17)*placeholders(16)),33)
						+ resize((coefficients(18)*placeholders(17)),33) + resize((coefficients(19)*placeholders(18)),33) + resize((coefficients(20)*placeholders(19)),33)
						+ resize((coefficients(21)*placeholders(20)),33) + resize((coefficients(22)*placeholders(21)),33) + resize((coefficients(23)*placeholders(22)),33)
						+ resize((coefficients(24)*placeholders(23)),33);
				end if;

			end if;
	
		end process;
		
		y <= std_logic_vector(temp(31 downto 15));			-- concurently assign the output y, take the 17 bits after the first 15 bits, since the fraction 
										-- is doubled when computing a product.
		
end myFIR;
