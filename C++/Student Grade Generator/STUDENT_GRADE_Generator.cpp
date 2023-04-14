	#include<iostream>
	#include<string>
	using namespace std;
	
	void Student_Info();
	void CourseMark();
	float CourseGrade(float);
	void Display_GradeRport();
	void Culculate_CGPA();
	void Status(float);
	
	struct student
	{
		int ID,age,year;
		char fname[30];
		char lname[30];
		char sex[16];
		char semester[30];
		float CGPA;
		struct 
		{
			float sub1;
			float sub2;
			float sub3;
			float sub4;
		}course;
		
		struct 
		{
			float sub1g;
			float sub2g;
			float sub3g;
			float sub4g;
		}grade;	
	};
	struct student s[300];
	int n;
	int main()
	{
    	cout<<"======================================================================\n";
    	cout<<"========== WELCOME TO AUTOMATED STUDENT GRADE REPORT SYSTEM ==========\n";
    	cout<<"==================== Created with C++ by Eshan =======================\n";
    	cout<<"======================================================================\n";
    
		Student_Info();
		CourseMark();
		Culculate_CGPA();
		Display_GradeRport();
		
    	cout<<"=====================================================================================\n";
    	cout<<"==============::THIS IS A MACHINE GENERATED GRADE REPORT OF STUDENT::================\n";
    	cout<<"=====================================================================================\n";
	}
	
	void Student_Info()
	{
		int i;
		cout<<"How many Students you have less than 100:";
		cin>>n;
		cout<<"\nSTUDENT INFORMATION:\n\n";
		
		for(i=0;i<n;i++)
		{
                cout<<"Enter FIRST Name of student_"<<i+1<< " : \n";
                cin>>s[i].fname;
                cout<<"Enter LAST Name of student_"<<i+1<< " : \n";
                cin>>s[i].lname;                
                cout<<"Enter ID NUMBER of student_"<<i+1<< " : \n";
                cin>>s[i].ID;
                cout<<"Enter GENDER of student_"<<i+1<< " : \n";
                cin>>s[i].sex;
                cout<<"Enter AGE of student_"<<i+1<< " : \n";
                cin>>s[i].age;
                cout<<"Enter YEAR student_"<<i+1<< " is studying in : \n";
                cin>>s[i].year;
                cout<<"Enter SEMESTER [in words] student_"<<i+1<< " is studying in : \n";
                cin>>s[i].semester;
		}
		
	}
	void CourseMark()
	{
		int mark;
		system("clear");
		cout<<"\n";
        cout<<"===========================================================\n";
        cout<<"===== Enter marks of studnets to corresponding course =====\n"; 
        cout<<"===========================================================\n";
		for(int i=0;i<n;i++)
		{
			cout<<"Enter student_"<<i+1<<" marks in SUBJECT_[1]:\n";
			cin>>s[i].course.sub1;
		
			cout<<"Enter student_"<<i+1<<" marks in SUBJECT_[2]:\n";
			cin>>s[i].course.sub2;
			
			cout<<"Enter student_"<<i+1<<" marks in SUBJECT_[3]:\n";
			cin>>s[i].course.sub3;
			
			cout<<"Enter student_"<<i+1<<" marks in SUBJECT_[4]:\n";
			cin>>s[i].course.sub4;
		}	
	}
	
	
	float CourseGrade(float mark)
	{
		float result;	 	 	 
				
				if(mark<=100 && mark>90)
				{
					result=4.0;
					cout<<"A+\n";
				}
				else if(mark<=90 && mark>85)
				{
					result=4;
					cout<<"A\n";
				}
				else if(mark<=85 && mark>80)
				{
					result=3.75;
					cout<<"A-\n";
				}
				else if(mark<=80 && mark>75)
				{
					result=3.5;
					cout<<"B+\n";
				}
				else if(mark<=75 && mark>70)
				{
					result=3;
					cout<<"B\n";
				}
				else if(mark<=70 && mark>65)
				{
					result=2.75;
					cout<<"B-\n";
				}
				else if(mark<=65 && mark>60)
				{
					result=2.5;
					cout<<"C+\n";
				}
				else if(mark<=60 && mark>50)
				{
					result=2;
					cout<<"C\n";
				}
				else if(mark<=50 && mark>45)
				{
					result=1.75;
					cout<<"C-\n";
				}
				else if(mark<=45 && mark>40)
				{
					result=1.5;
					cout<<"D+\n";
				}
				else if(mark<=40 && mark>30)
				{
					result=1;
					cout<<"D\n";
				}
				else if(mark<=30 && mark>0)
				{
					result=0;
					cout<<"F\n";	
				}
				else
				{
					cout<<"INVALID MARKS.\n";
				}
			return result;
	}
	
	
	void Culculate_CGPA()
	{
		int credit=5;
		int Tcredit=20;
		int a;
		float SumofGP[2]={0};
		for(a=0;a<n;a++)
		{
			if((s[a].grade.sub1g !=0 && s[a].grade.sub2g !=0) && (s[a].grade.sub4g !=0  && s[a].grade.sub3g !=0))
			{
				SumofGP[a]=(s[a].grade.sub1g * credit) + (s[a].grade.sub2g * credit) + (s[a].grade.sub4g * credit) + (s[a].grade.sub3g*credit);
			}
			else if(s[a].grade.sub1g ==0)
			{
				SumofGP[a]= (s[a].grade.sub2g * credit) + (s[a].grade.sub4g * credit) + (s[a].grade.sub3g*credit);
			}
			else if(s[a].grade.sub2g ==0)
			{
				SumofGP[a]=(s[a].grade.sub1g * credit) + (s[a].grade.sub4g * credit) + (s[a].grade.sub3g*credit);	   
			}
			else if(s[a].grade.sub3g ==0)
			{
				SumofGP[a]=(s[a].grade.sub1g * credit) + (s[a].grade.sub2g * credit) + (s[a].grade.sub4g * credit);
			}
			else
			{
				SumofGP[a]=(s[a].grade.sub1g * credit) + (s[a].grade.sub2g * credit) + (s[a].grade.sub3g*credit);
			}
			s[a].CGPA=SumofGP[a]/Tcredit;
		}
	}
	
	
	void Display_GradeRport()
	{
		int i,j;
		float alpha;
		system("clear");
		cout<<"\n";
        cout<<"===========================================================================\n";
        cout<<"======================== GRADE REPORT OF STUDENT ==========================\n";
        cout<<"===========================================================================\n";
		for(i=0;i<n;i++)
		{
			cout<<"Full Name: "<<s[i].fname<<" "<<s[i].lname<<".\n"<<"Year:"<<s[i].year<<"\nSemester: "<<s[i].semester<<endl;
			cout<<"Sex: "<<s[i].sex<<"\n"<<"Age: "<<s[i].age<<endl<<endl;
			
			for(j=0;j<4;j++)
			{
				if(j==0)
				{
					alpha=s[i].grade.sub1g;
					cout<<"GRADE IN SUBJECT_[1]:		\t";
					s[i].grade.sub1g=CourseGrade(s[i].course.sub1);
				}
				else if(j==1)
				{
					alpha=s[i].grade.sub2g;
					cout<<"GRADE IN SUBJECT_[2]:		\t";
					s[i].grade.sub2g=CourseGrade(s[i].course.sub2);
					
				}
				else if(j==2)
				{
					alpha=s[i].grade.sub3g;
					cout<<"GRADE IN SUBJECT_[3]:		\t";
					s[i].grade.sub3g=CourseGrade(s[i].course.sub3);
				}
				else
				{
					alpha=s[i].grade.sub4g;
					cout<<"GRADE IN SUBJECT_[4]:		\t";
					s[i].grade.sub4g=CourseGrade(s[i].course.sub4);
				}
			}
			
			Culculate_CGPA();
			cout<<"\n";
			cout<<">> CGPA = "<<s[i].CGPA<<endl;;
			
			Status(s[i].CGPA);	
			
			cout<<"===========================================================================\n";	
		
	}		
	}
		
	void Status(float sta)
	{
        if (sta==4)
        {
            cout<<"REMARKS::\n";
            cout<<"CONGRATULATIONS!VERY GOOD DISTINCTION.\n";
        }
        else if (sta>=3.75 && sta<=3.99)
        {
            cout<<"REMARKS::\n";
            cout<<"GOOD DISTINCTION\n";
        }
        else if (sta>=3.5 && sta<=3.74)
        {
            cout<<"REMARKS::\n";
            cout<<"DISTINCTION\n";
        }
        else if (sta>=3.49 && sta<=3.25)
        {
            cout<<"REMARKS::\n";
            cout<<"DEAN'S LIST\n";
        }
        else if (sta>=2.0 && sta<=3.24)
        {
            cout<<"REMARKS::\n";
            cout<<"NEED EFFORT...PROMOTED.\n";
        }
        else if (sta>=1.75 && sta<=1.99)
        {
            cout<<"REMARKS::\n";
            cout<<"GUIDENCE NEEDED...WARNING\n";
        }
        else if (sta>=1.0 && sta<=1.74)
        {
            cout<<"REMARKS::\n";
            cout<<"READMISSION\n";
        }
        else if (sta>=0.0 && sta<=1.0)
        {
            cout<<"REMARKS::\n";
            cout<<"NULL\n";
        }
	}
	

