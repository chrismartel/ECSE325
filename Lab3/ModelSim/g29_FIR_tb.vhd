library ieee;
use ieee.std_logic_1164.all;
use IEEE.NUMERIC_STD.ALL;
use STD.textio.all;
use ieee.std_logic_textio.all;

entity g29_FIR_tb is
end g29_FIR_tb;

architecture test of g29_FIR_tb is

-- Declare the component under test

	component g29_FIR is
		port(
			x 	: in std_logic_vector(15 downto 0);
			clk 	: in std_logic;
			rst	: in std_logic;
			enable  : in std_logic;
			y	: out std_logic_vector(16 downto 0)
		);
	end component g29_FIR;

-- Testbench internal signals

	file file_VECTORS_INPUTS : text;
	file file_RESULTS	 : text;
	
	constant clk_PERIOD : time := 100 ps;
	
	signal x_in	: std_logic_vector(15 downto 0);
	signal clk_in	: std_logic;
	signal rst_in	: std_logic;
	signal enable_in: std_logic;
	signal write_enable: std_logic := '0';
	signal y_out	: std_logic_vector(16 downto 0);

begin
-- Instantiate FIR
g29_FIR_INST : g29_FIR
	port map (
		x => x_in,
		clk => clk_in,
		rst => rst_in,
		enable => enable_in,
		y => y_out
);

-- clock generation process

clk_generation : process 
  begin 
      clk_in <= '1';
      wait for clk_PERIOD / 2; 
      clk_in <= '0';
      wait for clk_PERIOD / 2; 
end process clk_generation;

-- providing inputs process

feeding_inputs : process is
	variable v_Iline : line;					
	variable v_x_in  : std_logic_vector(15 downto 0);
   begin
        -- reset the circuit before providing inputs
        rst_in <= '1';
	
        wait until rising_edge(clk_in);
        wait until rising_edge(clk_in);
        rst_in <= '0';
        file_open(file_VECTORS_INPUTS, "lab3-In-converted.txt", read_mode);
        file_open(file_RESULTS, "lab3-results.txt", write_mode);
	
        while not endfile (file_VECTORS_INPUTS) loop
		readline(file_VECTORS_INPUTS, v_Iline);
		read(v_Iline,v_x_in);
		x_in <= v_x_in;
		enable_in <= '1';
		wait until rising_edge(clk_in);
		write_enable <= '1';
	end loop;	
	enable_in <= '0';
	wait until rising_edge(clk_in);
	write_enable <= '0';
	wait;
end process;

-- writing outputs process
writing_outputs : process(clk_in)
	variable v_Oline : line;
	variable v_y_in  : std_logic_vector(16 downto 0);
	begin
	if write_enable = '1' and clk_in'event and clk_in = '1' then 
        	write(v_Oline, y_out);
       		writeline(file_RESULTS, v_Oline);	
	end if;
end process;
end test;
	

