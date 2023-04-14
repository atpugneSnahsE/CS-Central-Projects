/*
Dated: 07/01/2022........02:55am
By Eshan Sengupta

A menu driven conversion program using look up tables and vice versa.
capable of performing
1.DEC-->BIN; 2.BIN-->DEC; 3.HEX-->BIN; 4.BIN-->HEX; 5.OCT-->BIN; 6.BIN-->OCT; 7.OCT-->DEC & 8.DEC-->OCT
where,
    BIN==>Binary
    DEC==>Decimal
    HEX==>Hexadecimal
    OCT==>Octal
*/
/*Call libraries*/
#include<iostream>
#include<fstream>
#include<string>
#include<sstream>
#include<algorithm>
#include<math.h>
#include<map>
using namespace std;

string BINtoOCT_Table(string num)
/*Binary to Octal convrsion Table*/
    {
        map<string, string> table;
        string conversion;
        table.insert(pair<string,string>("000", "0"));
        table.insert(pair<string,string>("001", "1"));
        table.insert(pair<string,string>("010", "2"));
        table.insert(pair<string,string>("011", "3"));
        table.insert(pair<string,string>("100", "4"));
        table.insert(pair<string,string>("101", "5"));
        table.insert(pair<string,string>("110", "6"));
        table.insert(pair<string,string>("111", "7"));
        conversion = table.find(num)->second;
        return conversion;
    }

string OCTtoBIN_Table(char num)
/*Octal to Binary convrsion Table*/
    {
        map<char, string> table;
        string conversion;
        table.insert(pair<char, string>('0', "000"));
        table.insert(pair<char, string>('1', "001"));
        table.insert(pair<char, string>('2', "010"));
        table.insert(pair<char, string>('3', "011"));
        table.insert(pair<char, string>('4', "100"));
        table.insert(pair<char, string>('5', "101"));
        table.insert(pair<char, string>('6', "110"));
        table.insert(pair<char, string>('7', "111"));
        conversion = table.find(num)->second;
        return conversion;
    }

string BINtoHEX_Table(string num)
/*Binary to Hexadecimal convrsion Table*/
    {
        map<string, string> table;
        string conversion;
        table.insert(pair<string,string>("0000", "0"));
        table.insert(pair<string,string>("0001", "1"));
        table.insert(pair<string,string>("0010", "2"));
        table.insert(pair<string,string>("0011", "3"));
        table.insert(pair<string,string>("0100", "4"));
        table.insert(pair<string,string>("0101", "5"));
        table.insert(pair<string,string>("0110", "6"));
        table.insert(pair<string,string>("0111", "7"));
        table.insert(pair<string,string>("1000", "8"));
        table.insert(pair<string,string>("1001", "9"));
        table.insert(pair<string,string>("1010", "A"));
        table.insert(pair<string,string>("1011", "B"));
        table.insert(pair<string,string>("1100", "C"));
        table.insert(pair<string,string>("1101", "D"));
        table.insert(pair<string,string>("1110", "E"));
        table.insert(pair<string,string>("1111", "F"));
        conversion = table.find(num)->second;
        return conversion;
    }
 string HEXtoBIN_Table(char num)
 /*Hexadecimal to Binary convrsion Table*/
    {
        map<char, string> table;
        string conversion;
        table.insert(pair<char, string>('0', "0000"));
        table.insert(pair<char, string>('1', "0001"));
        table.insert(pair<char, string>('2', "0010"));
        table.insert(pair<char, string>('3', "0011"));
        table.insert(pair<char, string>('4', "0100"));
        table.insert(pair<char, string>('5', "0101"));
        table.insert(pair<char, string>('6', "0110"));
        table.insert(pair<char, string>('7', "0111"));
        table.insert(pair<char, string>('8', "1000"));
        table.insert(pair<char, string>('9', "1001"));
        table.insert(pair<char, string>('A', "1010"));
        table.insert(pair<char, string>('B', "1011"));
        table.insert(pair<char, string>('C', "1100"));
        table.insert(pair<char, string>('D', "1101"));
        table.insert(pair<char, string>('E', "1110"));
        table.insert(pair<char, string>('F', "1111"));
        conversion = table.find(num)->second;
        return conversion;
    }

    int main(int argc, char* arv[])
        {
            /*Conversion Menu*/
            cout<<"Number Base Conversions"<<endl;
            cout<<"1. Convert from Decimal to Binary"<<endl;
            cout<<"2. Convert from Binary to Decimal"<<endl;
            cout<<"3. Convert from Hexadecimal to Binary"<<endl;
            cout<<"4. Convert from Binary to Hexadecimal"<<endl;
            cout<<"5. Convert from Octal to Binary"<<endl;
            cout<<"6. Convert from Binary to Octal"<<endl;
            cout<<"7. Convert from Octal to Decimal"<<endl;
            cout<<"8. Convert from Decimal to Octal"<<endl;           
            cout<<"9. End Program"<<endl;

            int input;
            cout<<"please enter number from the menu bar:: ";
            cin>> input;
                while (input != 9)/*End program*/
                {
                    if(input==1)/*Decimal to Binary*/
                    {
                            int num;
                            string remainder;
                            cout<<"please enter a Decimal Number:: ";
                            cin>> num;
                            int DEC =num;
                            while(num!=0)
                            {
                            int remain=num%2;
                            stringstream ss;
                            ss<<remain;
                            remainder += ss.str();
                            num/=2;
                            }
                            reverse(remainder.begin(), remainder.end());
                            cout<<DEC<<" in Binary is "<<remainder<<endl<<endl;
                    }

                    else if(input==2)/*Binary to Decimal*/
                    {
                        int DEC_Num;
                        string BIN_Num;
                        cout<<"The format for a Binary number shoud be in groups of four with  no spaces"<<endl;
                        cout<<"For Example::000110100011 = 419"<<endl;
                        cout<<"Please enter a Binary number::";
                        cin>>BIN_Num;
                        int power = BIN_Num.size()-1;
                            for(int i = 0; (unsigned)i<BIN_Num.size(); i++)
                            {
                            int num = BIN_Num[i]-48;
                            DEC_Num += num*pow(2, power);
                            power--;
                            }
                    cout<<BIN_Num<<" in Decimal is "<<DEC_Num<<endl<<endl;
                    }

                    else if(input==7)/*Octal to decimal*/
                    {
                        int DEC_Num;
                        string OCT_Num;
                        cout<<"Please enter a octal number:: ";
                        cin>>OCT_Num;
                        int power = OCT_Num.size()-1;
                            for(int i = 0;i<OCT_Num.size(); i++)
                            {
                            int num = OCT_Num[i]-48;
                            DEC_Num += num*pow(8, power);
                            power--;
                            }
                        cout<<OCT_Num<<" in Decimal is "<<DEC_Num<<endl<<endl;
                    }

                    else if(input==8)/*Decimal to octal*/
                    {
                            int num;
                            string remainder;
                            cout<<"please enter a Decimal Number:: ";
                            cin>> num;
                            int DEC =num;
                            while(num!=0)
                            {
                            int remain=num%8;
                            stringstream ss;
                            ss<<remain;
                            remainder += ss.str();
                            num/=8;
                            }
                            reverse(remainder.begin(), remainder.end());
                            cout<<DEC<<" in octal is "<<remainder<<endl<<endl;
                    }
                    else if(input==3)/*Hexadecimal nto binary*/
                    {
                        string BIN_Num;
                        string HEX_Num;
                        cout<<"Please enter a hexadecimal number::";
                        cin>>HEX_Num;
                        for (int i = 0; (unsigned)i<HEX_Num.size(); i++)
                        {
                            BIN_Num += HEXtoBIN_Table(HEX_Num[i]);
                        }
                        cout<<HEX_Num<<"in Binary is"<<BIN_Num<<endl<<endl;
                    }
                    else if(input==4)/*Binary to hexadecimal*/
                    {
                        string BIN_Num;
                        string HEX_Num;
                        string substring;

                    
                    cout<<"The format for a Binary number should be in groups of four with no spaces"<<endl;
                    cout<<"For example, 000110100011= 1A3"<<endl;
                    cin>> BIN_Num;
                    int i=0;
                    while((unsigned)i< BIN_Num.length())
                    {
                        int position=i;
                        substring=BIN_Num.substr(position, 4);
                        HEX_Num += BINtoHEX_Table(substring);
                        i+=4;
                    }
                    cout<< BIN_Num<<" in hexadecimal format is "<<HEX_Num<<endl<<endl;
                    }
                    else if(input==5)/*Octal to Binary*/
                    {
                        string BIN_Num;
                        string OCT_Num;
                        cout<<"Please enter a octal number::";
                        cin>>OCT_Num;
                        for (int i = 0; (unsigned)i<OCT_Num.size(); i++)
                        {
                            BIN_Num += OCTtoBIN_Table(OCT_Num[i]);
                        }
                        cout<<OCT_Num<<" in Binary is "<<BIN_Num<<endl<<endl;
                    }
                    else if(input==6)/*Binary to Octal*/
                    {
                        string BIN_Num;
                        string OCT_Num;
                        string substring;

                    
                    cout<<"The format for a Binary number should be in groups of three with no spaces"<<endl;
                    cout<<"For example, 010111001= 271"<<endl;
                    cin>> BIN_Num;
                    int i=0;
                    while((unsigned)i< BIN_Num.length())
                    {
                        int position=i;
                        substring=BIN_Num.substr(position, 3);
                        OCT_Num += BINtoOCT_Table(substring);
                        i+=3;
                    }
                    cout<< BIN_Num<<" in octal format is "<<OCT_Num<<endl<<endl;
                    }                    
                    else
                    {
                        cout<<input<<"is not avalid option. Please select again::"<<endl;
                        cout<<"please Enter a number from the Menu::";
                        cin>>input;
                    }
                            cout<<"Number Base Conversions"<<endl;
                            cout<<"1. Convert from Decimal to Binary"<<endl;
                            cout<<"2. Convert from Binary to Decimal"<<endl;
                            cout<<"3. Convert from Hexadecimal to Binary"<<endl;
                            cout<<"4. Convert from Binary to Hexadecimal"<<endl;
                            cout<<"5. Convert from Octal to Binary"<<endl;
                            cout<<"6. Convert from Binary to Octal"<<endl;
                            cout<<"7. Convert from Octal to Decimal"<<endl;
                            cout<<"8. Convert from Decimal to Octal"<<endl;  
                            cout<<"9. End Program"<<endl;
                    cout<<"Please enter a number from the menu::";
                    cin>> input;
                }
        cout<<"Thank you for using Automated Number Base Conversion System"<<endl;
        }