library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity g29_MAC is
	port( x : in std_logic_vector(9 downto 0);							--first input of length 10 bits
			y : in std_logic_vector(9 downto 0);   						--second input of length 10 bits
			N : in std_logic_vector(9 downto 0);							--total number of inputs maximum 1024
			clk : in std_logic; 													--clock
			rst : in std_logic;													--asynchronous reset acive high
			mac : out std_logic_vector(22 downto 0); 						--output of MAC unit of length 23 bits
			ready : out std_logic);												--denotes the validity of the mac signal
end g29_MAC;

architecture myMAC of g29_MAC is
	signal accumulation : signed (22 downto 0);							--signal to keep track of accumulation on MAC
	signal count : unsigned(9 downto 0);									--count of MAC 1...N
	begin
		macProcess: process(clk,rst)
			begin
				if rst = '1' then													--asynchronous reset
						count <= (others => '0');								--reset count
						ready<='0';													--set ready to 0 to indicate that the MAC computation is not over
						accumulation<=(others => '0');						--reset value of accumulation
				elsif clk'event and clk = '1' then 							--synchronous process with clock rising edge
				
					if count < unsigned(N) then								--if count is smaller than the number of inputs

						accumulation<= signed(accumulation)+resize((signed(y)*signed(x)),23);	--compute synchronously next result on MAC unit						
						
					elsif count = unsigned(N) then							--if count is equal to N, all products were summed, MAC is done

						ready<='1';													--set ready to 1 to indicate that MAC is done
						
					end if;
					count<=count +"0000000001";								--increment count

				end if;
			end process;
			mac <= std_logic_vector(accumulation);							--set the mac output to the accumulation value asynchronously
end myMac;