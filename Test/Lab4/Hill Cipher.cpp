
#include <iostream> 

using namespace std; 

int keyMatrix[3][3];

int cipherMatrix[3][1]; 

int messageVector[3][1];

string CipherText; 

void Decri();

void Hil(string df);

void getKeyMatrix(string key, int keyMatrix[][3]) 

{ 

    int k = 0; 

    for (int i = 0; i < 3; i++)  

    { 

        for (int j = 0; j < 3; j++)  

        { 

            keyMatrix[i][j] = (key[k]) % 65; 

            k++; 

        } 

    } 

} 

  

// Following function encrypts the message 

void encrypt(int cipherMatrix[][1], 

             int keyMatrix[][3],  

             int messageVector[][1]) 

{ 

    int x, i, j; 

    for (i = 0; i < 3; i++)  

    { 

        for (j = 0; j < 1; j++) 

        { 

            cipherMatrix[i][j] = 0; 

           

             for (x = 0; x < 3; x++) 

            { 

                cipherMatrix[i][j] +=  

                     keyMatrix[i][x] * messageVector[x][j]; 

            } 

          

            cipherMatrix[i][j] = cipherMatrix[i][j] % 26; 

        } 

    } 

} 

 

void HillCipher(string message, string key) 

{ 

    

     

    getKeyMatrix(key, keyMatrix); 

  

     

  

    

    for (int i = 0; i < 3; i++) 

        messageVector[i][0] = (message[i]) % 65; 

  

    

  

    

    encrypt(cipherMatrix, keyMatrix, messageVector); 

  

    

  

    

    for (int i = 0; i < 3; i++) 

        CipherText += cipherMatrix[i][0] + 65; 

  

    

      cout << " Ciphertext:" << CipherText; 

    

      Hil(CipherText);

} 

void Hil(string message) 

{ 

    

     

    //getKeyMatrix(key, keyMatrix); 

  

      Decri();

    string dec;

    

    for (int i = 0; i < 3; i++) 

        messageVector[i][0] = (message[i]) % 65; 

       encrypt(cipherMatrix, keyMatrix, messageVector); 

    for (int i = 0; i < 3; i++) 
        dec+= cipherMatrix[i][0] + 65;    
    cout << "\n Decripted text:" << dec; 

}
void Decri()
{

	keyMatrix[0][0]=8;

	keyMatrix[0][1]=5;

	keyMatrix[0][2]=10;

	keyMatrix[1][0]=21;

	keyMatrix[1][1]=8;

	keyMatrix[1][2]=21;

	keyMatrix[2][0]=21;

	keyMatrix[2][1]=12;

	keyMatrix[2][2]=8;

}
int main() 
{ 

    string message = "ACT";

    string key = "GYBNQKURP"; 

    HillCipher(message, key); 

  

    return 0; 

} 

