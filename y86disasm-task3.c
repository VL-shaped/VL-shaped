#include <stdio.h>
#include <stdlib.h>

const char* register_names[] =
{
 "%eax",
 "%ecx",
 "%edx",
 "%ebx",
 "%esp",
 "%ebp",
 "%esi",
 "%edi",
 "UNKNOWN_REGSITER"
};

//This is the help function that reads y86 binary code from a file 
unsigned int loadBinFromFile( const char* filename, unsigned char memory[], unsigned int memsize );

int main ( int argc, char** argv )
{
  unsigned int MAX_MEMSIZE = 4096;
  unsigned char memory[MAX_MEMSIZE]; //This array represents the 4KB memory space
  unsigned int PC = 0; //This is the initial program counter address
  

  if (argc < 2 )
  {
    printf("Usage: please specify a y86 binary program file in the argument.\n");
    return -1;
  }

  unsigned int program_size = loadBinFromFile(argv[1], memory, MAX_MEMSIZE);

  if(program_size == 0 ) return 0;

  //TODO: Implement your disassembler inside the loop
  //At the moment, it simply display each byte from the memory.
  while( PC < program_size)
  {
    if ( memory[PC] == 0x30 )
      {
        unsigned int immValue = memory[PC+2] ^ memory[PC+3]<<8 ^ memory[PC+4]<<16 ^ memory[PC+5]<<24;
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("irmovl ");
        printf("$%d, ", immValue);
        printf("%s\n", register_names[regB]);
        PC+=6;
      }
    else if ( memory[PC] == 0x50 )
      {
        unsigned int immValue = memory[PC+2] ^ memory[PC+3]<<8 ^ memory[PC+4]<<16 ^ memory[PC+5]<<24;
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("mrmovl ");
        printf("%d(%s), ", immValue, register_names[regB]);
        printf("%s\n", register_names[regA]);
        PC+=6;
      }
    else if ( memory[PC] == 0x40 )
      {
        unsigned int immValue = memory[PC+2] ^ memory[PC+3]<<8 ^ memory[PC+4]<<16 ^ memory[PC+5]<<24;
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("rmmovl ");
        printf("%s, ", register_names[regA]);
        printf("%d(%s)\n", immValue, register_names[regB]);
        PC+=6;
      }
    else if ( memory[PC] == 0x20 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("rrmovl ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x00 ) //byte 0 is the opcode
      {
        printf("halt\n"); //opcode 00 is halt
      }
      else if ( memory[PC] == 0x10 )
      {
        printf("nop\n");
      }
      else if ( memory[PC] == 0x70 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24; //a memory address, byte1->byte4 = 32bit integer
        printf("jmp ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x71 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("jle ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x72 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("jl ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x73 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("je ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x74 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("jne ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x75 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("jge ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x76 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("jg ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0xA0 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("pushl ");
        printf("%s\n", register_names[regA]);
        PC+=2;
      }
      else if ( memory[PC] == 0xB0 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("popl ");
        printf("%s\n", register_names[regA]);
        PC+=2;
      }
      else if ( memory[PC] == 0x80 )
      {
        unsigned int memAddress = memory[PC+1] ^ memory[PC+2]<<8 ^ memory[PC+3]<<16 ^ memory[PC+4]<<24;
        printf("call ");
        printf("%u\n", memAddress);
        PC+=5;
      }
      else if ( memory[PC] == 0x60 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("addl ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x61 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("subl ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x62 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("andl ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x63 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("xorl ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x21 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("cmovle ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x22 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("cmovl ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x23 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("cmove ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x24 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("cmovne ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x25 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("cmovge ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x26 )
      {
        unsigned int registers = memory[PC+1]; // byte0=opcode, byte1=two registers
        unsigned int regA = memory[PC+1]>>4; //register 1
        unsigned int regB = memory[PC+1]&0xf; //register 2
        printf("cmovg ");
        printf("%s, %s\n", register_names[regA],register_names[regB]);
        PC+=2;
      }
      else if ( memory[PC] == 0x90 )
      {
        printf("ret\n");
      }
  }
  return 0;
}

/****************************************************************************
N.B. You do not need to modify or work in this function.
Description:
This function reads in a y86 machine bytecode from file and
store them in an unsigned char array.
******************************************************************************/
unsigned int loadBinFromFile( const char* filename, unsigned char memory[], unsigned int memsize )
{
  unsigned int bytes_read = 0;

  unsigned int file_size = 0;

  FILE* pfile = fopen(filename, "rb");

  if(!pfile)
  {
    printf("Unable to load file %s, please check if the path and name are correct.\n", filename);
    return 0;
  }

  fseek(pfile, 0, SEEK_END);
  file_size = ftell(pfile);
  rewind(pfile);

  if(file_size > memsize)
  {
    printf("Program size exceeds memory size of %d.\n", memsize);
    return 0;
  }

  bytes_read = fread(memory, 1, file_size, pfile);

  if(bytes_read != file_size)
  {
    printf("Bytes read does not match the file size.\n");
    return 0;
  }

  fclose(pfile);

  return bytes_read;
}
