/*  BIN=Binary
    DEC=Decimal
    HEX=Hexadecimal
*/
#include<iostream>
#include<fstream>
#include<string>
#include<sstream>
#include<algorithm>
#include<math.h>
#include<map>
using namespace std;

string BINtoHEX_Table(string num)
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
            cout<<"Binary Conversions"<<endl;
            cout<<"1. Convert Decimal to Binary"<<endl;
            cout<<"2. Convert Binary to Decimal"<<endl;
            cout<<"3. Convert Hexadecimal to Binary"<<endl;
            cout<<"4. Convert Binary to Hexadecimal"<<endl;
            cout<<"5. End Program"<<endl;

            int input;
            cout<<"please enter number from the menu bar:: ";
            cin>> input;
                while (input != 5)
                {
                    if(input==1)
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
                    else if(input==2)
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
                    else if(input==3)
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
                    else if(input==4)
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
                    else
                    {
                        cout<<input<<"is not avalid option. Please select again::"<<endl;
                        cout<<"please Enter a number from the Menu::";
                        cin>>input;
                    }
                    cout<<"Binary Conversions"<<endl;
                    cout<<"1. Convert Decimal to Binary"<<endl;
                    cout<<"2. Convert Binary to Decimal"<<endl;
                    cout<<"3. Convert Hexadecimal to Binary"<<endl;
                    cout<<"4. Convert Binary to Hexadecimal"<<endl;
                    cout<<"5. End Program"<<endl;
                    cout<<"Please enter a number from the menu::";
                    cin>> input;
                }
        cout<<"Thankyou for using Automated Conversion System"<<endl;
        }
