library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity g29_MAC is
	port( x : in std_logic_vector(9 downto 0);	--first input, you have to first calculate length
			y : in std_logic_vector(9 downto 0);   --second input, replace ?? with your length
			N : in std_logic_vector(9 downto 0);	--total number of inputs
			clk : in std_logic; 							--clock
			rst : in std_logic;							--asynchronous reset acive high)
			mac : in std_logic_vector(19 downto 0); --output of MAC unit, replace the length
			ready : in std_logic);						--denotes the validit of the mac signal
end g29_MAC;

architecture myMAC of g29_MAC is
	signal sum : signed (19 downto 0);				--sum of MAC
	signal product : signed (19 downto 0);			--poduct of MAC
	signal count : std_logic (9 downto 0);			--count of MAC 1...N
	begin
		macProcess: process(clk)
			begin
				if clk'event and clk = '1' then 		--synchronous process with clock rising edge
					if rst = '1' then
						sum <=(others => '0');			--reset entire number
						ready<='1';							--set read input such as in the pseudocode
					elsif count<N then					--product unit
						product<=signed(y)*signed(x);
						sum<=sum+product;
						count<=std_logic_vector(signed(count)+1);	
					elsif count = N then
						mac<=std_logic_vector(sum);
						ready<='1';
						
					end if;
				end if;
			end process;
	
end myMac;