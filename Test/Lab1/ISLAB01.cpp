#include<bits/stdc++.h>
using namespace std;
int main()
{
	string str;
	cout<<"Enter the string ";
	cin>>str;
	for(int j=0;j<str.size();j++)
	{
		if(str[j]>='A'&&str[j]<='Z')
		str[j]=char((str[j]-'A'+25)%26+'A');
		else
		str[j]=char((str[j]-'a'+25)%26+'a');
	}
	cout<<"String after rotation: ";
	cout<<str<<endl;
	return 0;
}
