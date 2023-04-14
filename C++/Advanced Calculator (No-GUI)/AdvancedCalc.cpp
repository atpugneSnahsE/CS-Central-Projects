#include<iostream>
#include<cmath>
using namespace std;
void arithmatic()
/*For arithmatic functions*/
{
    int op =0;
    float A=0;
    float B=0;

    cout<<"[1] Addition\n";
    cout<<"[2] Substraction\n";
    cout<<"[3] Product\n";
    cout<<"[4] Division\n";
    cout<<"[5] Root\n";
    cout<<"Select Operation from the menu::\n";

    cin>>op;
    cout<<"Enter number:: ";
    cin>>A;
    cout<<"Enter second number:: ";
    cin>>B;
    cout<<"RESULT:: ";

    switch(op)
    {
        case 1:
            cout<<"Addition of the numbers is::"<<(A+B);
            break;
        case 2:
            cout<<"Substraction of the numbers is::"<<(A-B);
            break;
        case 3:
            cout<<"Multiplication of the numbers is::"<<(A*B);
            break;
        case 4:
            cout<<"Division of the numbers is::"<<(A/B);
        default:
            cout<<"Invalid Operation";
            break;
    }
    cout<<endl;
}
/*For trignometric functions*/
void trignometric()
{
    int op=0;
    float val=0.0;
    cout<<"Select from the menu\n";
    cout<<"[1] Sine\n";
    cout<<"[2] Cosine\n";
    cout<<"[3] Tangent\n";
    cout<<"[4] Cosecant\n";
    cout<<"[5] Secant\n";
    cout<<"[6] Cotangent\n";
    cout<<"Op:: ";
    cin>>op;
    cout<<"Enter value:: ";
    cin>>val;
    if(op==1)
        {
            cout<<sin(val);
        }
    else if(op==2)
        {
            cout<<cos(val);
        }
    else if(op==3)
        {
            cout<<tan(val);
        }
    else if(op==4)
        {
            cout<<asin(val);
        }
    else if(op==5)
        {
            cout<<acos(val);
        }
    else if(op==6)
        {
            cout<<atan(val);
        }
    else
        {
            cout<<"INVALID Operation";
        }
    cout<<endl;
}
/*For exponentional functions*/
void exponential()
{
    float base=0.0;
    float eee=0.0;
    cout<<"Enter Base::";
    cin>>base;
    cout<<"Enter exponent::";
    cin>>eee;
    cout<<pow(base, eee)<<endl;
}
/*For logarithmic functions*/
void logarithmic()
{
    float value=0.0;
    cout<<"Enter the value to calculate the log(e)::";
    cin>>value;
    cout<<log(value)<<endl;
}
/*For root functions*/
void root()
{
    float value=0.0;
    cout<<"Enter Value::";
    cin>>value;
    cout<<sqrt(value)<<endl;
}
/*Main calculator finction*/
int main()
    {
        int sel=0;
        char choice='y';
        cout<<"Advanced Calculator\n";
        cout<<"[1] Arithmatic Functions\n";
        cout<<"[2] Trignometric Functions\n";
        cout<<"[3] Exponential Functions\n";
        cout<<"[4] Logarithmic Functions\n";
        cout<<"[5] Root Functions\n";
        cout<<"===Please choose one from the list===\n";

        while(choice=='y')
        {
            cout<<"Enter the type of operation you want to calculate:: ";
            cin>>sel;
            switch(sel)
            {
                case 1:
                    arithmatic();
                    break;
                case 2:
                    trignometric();
                    break;
                case 3:
                    exponential();
                    break;
                case 4:
                    logarithmic();
                    break;
                case 5:
                    root();
                    break;                    
                default:
                    cout<<"INVALID Operation ! ";
            }
        cout<<"Want to CONTINUE further operations? y/n"<<endl;
        cin>>choice;
        if(choice=='n')
            {
            cout<<"Thankyou for using ADVANCED CALCULATOR";
            break;
            }
        }
    return 0;
    }


