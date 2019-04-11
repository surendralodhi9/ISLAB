#Team Members: 66,80
from itertools import cycle
import base64
from cryptography.fernet import Fernet
import pyperclip
import pyDes
import binascii
import codecs
import getopt, os, sys, math, struct, wave
from PIL import Image
def my_xor():
    message = ''
    print('enter a msg')
    message=input()
    key = ''
    print('enter the key')
    key=input()
    cyphered = ''.join(chr(ord(c)^ord(k)) for c,k in zip(message, cycle(key)))
    print('%s ^ %s = %s' % (message, key, cyphered))
    message = ''.join(chr(ord(c)^ord(k)) for c,k in zip(cyphered, cycle(key)))
    print('%s ^ %s = %s' % (cyphered, key, message))
    return
    


def my_base64():
    print('enter msg')
    msg=str(input())
    bytes1=bytes(msg,'utf-8')
    
    encoded_data = base64.b64encode(bytes1)
    print("Encoded text with base 64 is")
    print(encoded_data.decode())
    decoded_data = base64.b64decode(encoded_data)
    print("decoded text is ")
    print(decoded_data.decode())
    return

def my_fernet():
    print('enter msg')
    msg=str(input())
    bytes1=bytes(msg,'utf-8')
    key = Fernet.generate_key()
    cipher_suite = Fernet(key)
    cipher_text = cipher_suite.encrypt(bytes1)
    print('cipher_text= ',cipher_text.decode())
    plain_text = cipher_suite.decrypt(cipher_text)
    print('plain_text= ',plain_text.decode())





    
def my_des():
    data = ''
    print('enter a msg')
    data=input()
    k = pyDes.des("DESCRYPT", pyDes.CBC, "\0\0\0\0\0\0\0\0", pad=None, padmode=pyDes.PAD_PKCS5)
    d = k.encrypt(data)
    print("Encrypted: " , d)
    print("Decrypted: " , (k.decrypt(d)))
    



def ceaser_encrypt(text,s): 
    result = "" 
  
    
    for i in range(len(text)): 
        char = text[i] 
  
        
        if (char.isupper()): 
            result += chr((ord(char) + s-65) % 26 + 65) 
  
         
        else: 
            result += chr((ord(char) + s - 97) % 26 + 97) 
  
    return result
def ceaser_decript(text,s):
    result=""
    for i in range(len(text)): 
        char = text[i] 
  
       
        if (char.isupper()): 
            result += chr((ord(char) - s-65) % 26 + 65) 
  
         
        else: 
            result += chr((ord(char) - s - 97) % 26 + 97) 
  
    return result
    

def my_chipher():
    print("Enter the message")
    text = input()
    s = 4
    print("Text  : " + text )
    print("Shift : " + str(s))
    encripted=ceaser_encrypt(text,s)
    print("Cipher: " +encripted) 
    print("Original message: ",ceaser_decript(encripted,s))


def my_rsa():
    n = 9516311845790656153499716760847001433441357    # p*q = modulus
    e = 65537
    d = 5617843187844953170308463622230283376298685
 
    message=''
    print('enter a msg:');
    message=input()
    print('message                   ', message)
 
    hex_data   = binascii.hexlify(message.encode())
    print('hex data                ', hex_data.decode())
 
    plain_text = int(hex_data, 16)
    print('plain text integer      ', plain_text)
 
    if plain_text > n:
      raise Exception('plain text too large for key')
 
    encrypted_text = pow(plain_text,     e, n)
    print('encrypted text integer  ', encrypted_text)
 
    decrypted_text = pow(encrypted_text, d, n)
    print('decrypted text integer  ', decrypted_text)
 
    print('message                 ', binascii.unhexlify(hex(decrypted_text)[2:]).decode())  


def my_rot13():
    print('enter a msg')
    msg=input()
    encr=codecs.encode(msg, 'rot_13')
    print('decrypted msg is:',encr)
    decr=codecs.decode(encr, 'rot_13')
    print('encrypted msg is: ',decr)

def genData(data):
    
    newd = [] 
		
    for i in data:
        newd.append(format(ord(i), '08b')) 
    return newd 
		
# Pixels are modified according to the 
# 8-bit binary data and finally returned 
def modPix(pix, data): 
	
	datalist = genData(data) 
	lendata = len(datalist) 
	imdata = iter(pix) 

	for i in range(lendata): 
		
		# Extracting 3 pixels at a time 
		pix = [value for value in imdata.__next__()[:3] +
								imdata.__next__()[:3] +
								imdata.__next__()[:3]] 
									
		# Pixel value should be made 
		# odd for 1 and even for 0 
		for j in range(0, 8): 
			if (datalist[i][j]=='0') and (pix[j]% 2 != 0): 
				
				if (pix[j]% 2 != 0): 
					pix[j] -= 1
					
			elif (datalist[i][j] == '1') and (pix[j] % 2 == 0): 
				pix[j] -= 1
				
		# Eigh^th pixel of every set tells 
		# whether to stop ot read further. 
		# 0 means keep reading; 1 means the 
		# message is over. 
		if (i == lendata - 1): 
			if (pix[-1] % 2 == 0): 
				pix[-1] -= 1
		else: 
			if (pix[-1] % 2 != 0): 
				pix[-1] -= 1

		pix = tuple(pix) 
		yield pix[0:3] 
		yield pix[3:6] 
		yield pix[6:9] 

def encode_enc(newimg, data): 
	w = newimg.size[0] 
	(x, y) = (0, 0) 
	
	for pixel in modPix(newimg.getdata(), data): 
		
		# Putting modified pixels in the new image 
		newimg.putpixel((x, y), pixel) 
		if (x == w - 1): 
			x = 0
			y += 1
		else: 
			x += 1
			
# Encode data into image 
def encode(): 
	img = input("Enter image name(with extension): ") 
	image = Image.open(img, 'r') 
	
	data = input("Enter data to be encoded : ") 
	if (len(data) == 0): 
		raise ValueError('Data is empty') 
		
	newimg = image.copy() 
	encode_enc(newimg, data) 
	
	new_img_name = input("Enter the name of new image(with extension .png): ") 
	newimg.save(new_img_name, str(new_img_name.split(".")[1].upper())) 

# Decode the data in the image 
def decode(): 
	img = input("Enter image name(with extension) :") 
	image = Image.open(img, 'r') 
	
	data = '' 
	imgdata = iter(image.getdata()) 
	
	while (True): 
		pixels = [value for value in imgdata.__next__()[:3] +
								imgdata.__next__()[:3] +
								imgdata.__next__()[:3]] 
		# string of binary data 
		binstr = '' 
		
		for i in pixels[:8]: 
			if (i % 2 == 0): 
				binstr += '0'
			else: 
				binstr += '1'
				
		data += chr(int(binstr, 2)) 
		if (pixels[-1] % 2 != 0): 
			return data 

def main(): 
	a = int(input(":: Welcome to Steganography ::\n"
						"1. Encode\n 2. Decode\n")) 
	if (a == 1): 
		encode() 
		
	elif (a == 2): 
		print("Decoded word- " + decode()) 
	else: 
		raise Exception("Enter correct input") 
		
import getopt, os, sys, math, struct, wave
num_lsb=0
def print_usage():
    print("\nUsage options:\n",
        "-h, --hide     If present, the script runs to hide data\n",
        "-r, --recover  If present, the script runs to recover data\n",
        "-s, --sound    What follows is the name of carrier wav file\n",
        "-d, --data     What follows is the file name having data to hide\n",
        "-o, --output   Output filename of choice\n",
        "-n, --nlsb     Number of LSBs to use\n",
        "-b, --bytes    Number of bytes to recover\n"
        " --help         Display help\n")

def prepare(sound_path):
    global sound, params, n_frames, n_samples, fmt, mask, smallest_byte
    sound = wave.open(sound_path, "r")
    
    params = sound.getparams()
    num_channels = sound.getnchannels()
    sample_width = sound.getsampwidth()
    n_frames = sound.getnframes()
    n_samples = n_frames * num_channels

    if (sample_width == 1):  # samples are unsigned 8-bit integers
        fmt = "{}B".format(n_samples)
        # Used to set the least significant num_lsb bits of an integer to zero
        mask = (1 << 8) - (1 << num_lsb)
        # The least possible value for a sample in the sound file is actually
        # zero, but we don't skip any samples for 8 bit depth wav files.
        smallest_byte = -(1 << 8)
    elif (sample_width == 2):  # samples are signed 16-bit integers
        fmt = "{}h".format(n_samples)
        # Used to set the least significant num_lsb bits of an integer to zero
        mask = (1 << 15) - (1 << num_lsb)
        # The least possible value for a sample in the sound file
        smallest_byte = -(1 << 15)
    else:
        # Python's wave module doesn't support higher sample widths
        raise ValueError("File has an unsupported bit-depth")

def hide_data(sound_path, file_path, output_path, num_lsb):
    global sound, params, n_frames, n_samples, fmt, mask, smallest_byte
    prepare(sound_path)
    # We can hide up to num_lsb bits in each sample of the sound file
    max_bytes_to_hide = (n_samples * num_lsb) // 8
    filesize = os.stat(file_path).st_size
    
    if (filesize > max_bytes_to_hide):
        required_LSBs = math.ceil(filesize * 8 / n_samples)
        raise ValueError("Input file too large to hide, "
                         "requires {} LSBs, using {}"
                         .format(required_LSBs, num_lsb))
    
    print("Using {} B out of {} B".format(filesize, max_bytes_to_hide))
    
    
    # Put all the samples from the sound file into a list
    raw_data = list(struct.unpack(fmt, sound.readframes(n_frames)))
    sound.close()
    
    input_data = memoryview(open(file_path, "rb").read())
    
    # The number of bits we've processed from the input file
    data_index = 0
    sound_index = 0
    
    # values will hold the altered sound data
    values = []
    buffer = 0
    buffer_length = 0
    done = False
    
    while(not done):
        while (buffer_length < num_lsb and data_index // 8 < len(input_data)):
            # If we don't have enough data in the buffer, add the
            # rest of the next byte from the file to it.
            buffer += (input_data[data_index // 8] >> (data_index % 8)
                        ) << buffer_length
            bits_added = 8 - (data_index % 8)
            buffer_length += bits_added
            data_index += bits_added
            
        # Retrieve the next num_lsb bits from the buffer for use later
        current_data = buffer % (1 << num_lsb)
        buffer >>= num_lsb
        buffer_length -= num_lsb

        while (sound_index < len(raw_data) and
               raw_data[sound_index] == smallest_byte):
            # If the next sample from the sound file is the smallest possible
            # value, we skip it. Changing the LSB of such a value could cause
            # an overflow and drastically change the sample in the output.
            values.append(struct.pack(fmt[-1], raw_data[sound_index]))
            sound_index += 1

        if (sound_index < len(raw_data)):
            current_sample = raw_data[sound_index]
            sound_index += 1

            sign = 1
            if (current_sample < 0):
                # We alter the LSBs of the absolute value of the sample to
                # avoid problems with two's complement. This also avoids
                # changing a sample to the smallest possible value, which we
                # would skip when attempting to recover data.
                current_sample = -current_sample
                sign = -1

            # Bitwise AND with mask turns the num_lsb least significant bits
            # of current_sample to zero. Bitwise OR with current_data replaces
            # these least significant bits with the next num_lsb bits of data.
            altered_sample = sign * ((current_sample & mask) | current_data)

            values.append(struct.pack(fmt[-1], altered_sample))

        if (data_index // 8 >= len(input_data) and buffer_length <= 0):
            done = True
        
    while(sound_index < len(raw_data)):
        # At this point, there's no more data to hide. So we append the rest of
        # the samples from the original sound file.
        values.append(struct.pack(fmt[-1], raw_data[sound_index]))
        sound_index += 1
    
    sound_steg = wave.open(output_path, "w")
    sound_steg.setparams(params)
    sound_steg.writeframes(b"".join(values))
    sound_steg.close()
    print("Data hidden over {} audio file".format(output_path))

def recover_data(sound_path, output_path, num_lsb, bytes_to_recover):
    # Recover data from the file at sound_path to the file at output_path
    global sound, n_frames, n_samples, fmt, smallest_byte
    prepare(sound_path)

    # Put all the samples from the sound file into a list
    raw_data = list(struct.unpack(fmt, sound.readframes(n_frames)))
    # Used to extract the least significant num_lsb bits of an integer
    mask = (1 << num_lsb) - 1
    output_file = open(output_path, "wb+")
    
    data = bytearray()
    sound_index = 0 
    buffer = 0
    buffer_length = 0
    sound.close()

    while (bytes_to_recover > 0):
        
        next_sample = raw_data[sound_index]
        if (next_sample != smallest_byte):
            # Since we skipped samples with the minimum possible value when
            # hiding data, we do the same here.
            buffer += (abs(next_sample) & mask) << buffer_length
            buffer_length += num_lsb
        sound_index += 1
        
        while (buffer_length >= 8 and bytes_to_recover > 0):
            # If we have more than a byte in the buffer, add it to data
            # and decrement the number of bytes left to recover.
            current_data = buffer % (1 << 8)
            buffer >>= 8
            buffer_length -= 8
            data += struct.pack('1B', current_data)
            bytes_to_recover -= 1

    output_file.write(bytes(data))
    output_file.close()
    print("Data recovered to {} text file".format(output_path))

def my_Audio():

    #################################
    hiding_data = False
    recovering_data = False
    
    print("1. for encode")
    print("2. for decode")
    ch=int(input())
    if (ch==int(1)):
        hiding_data=True
        sound_path="opera.wav"
        file_path ="data.txt"
        output_path="opera_steg.wav"
        num_lsb=int(2)
    else:
        recovering_data = True
        sound_path="opera_steg.wav"
        output_path="data_steg.txt"
        num_lsb=int(2)
        bytes_to_recover=int(10)
        
        
    try:
        
        
        if (hiding_data):
            
            print(output_path)
            hide_data(sound_path, file_path, output_path, num_lsb)
        if (recovering_data):
            recover_data(sound_path, output_path, num_lsb, bytes_to_recover)
    except Exception as e:
        print("Ran into an error during execution. Check input and try again.\n")
        print(e)
        print_usage()
        sys.exit(1)
    

def my_Watermark():
    lgo=input("Enter the logo name")

    path = "C:/Users/Administrator/Desktop/Clone/final/images"
    pos = "topleft"
    ####################################################################
    logo = Image.open(lgo)
    logoWidth = logo.width
    logoHeight = logo.height


    for filename in os.listdir(path):
        
        
        if (filename.endswith('.jpg') or filename.endswith('.png')) and (filename != lgo):
            image = Image.open(path + '/' + filename)
            imageWidth = image.width
            imageHeight = image.height

            try:
                
                
                if pos == 'topleft':
                    image.paste(logo, (0, 0), logo)
                elif pos == 'topright':
                    image.paste(logo, (imageWidth - logoWidth, 0), logo)
                elif pos == 'bottomleft':
                    image.paste(logo, (0, imageHeight - logoHeight), logo)
                elif pos == 'bottomright':
                    image.paste(logo, (imageWidth - logoWidth, imageHeight - logoHeight), logo)
                elif pos == 'center':
                    image.paste(logo, ((imageWidth - logoWidth)/2, (imageHeight - logoHeight)/2), logo)
                else:
                    print('Error: ' + pos + ' is not a valid position')
                    print('Usage: watermark.py \'image path\' \'logo path\' [topleft, topright, bottomleft, bottomright, center]')

                image.save(path + '/' + filename)
                print('Added watermark to ' + path + '/' + filename)

            except:
                
                image.paste(logo, ((imageWidth - logoWidth)/2, (imageHeight - logoHeight)/2), logo)
                image.save(path + '/' + filename)
                print('Added default watermark to ' + path + '/' + filename)


while(True):
    print('\n\n\n1.xor \n2.base64 \n3.Fernet \n4.DES \n5.ciser_chipher \n6.rsa \n7.ROT')
    print("8.Image")
    print("9.Water mark")
    print("10.Audio")
    print(".Exit\n")
    ch=int(input())
    if(ch==int(1)):
        my_xor()
    elif(ch==int(2)):
        my_base64()
    elif(ch==int(3)):
        my_fernet()
    elif(ch==int(4)):
        my_des()
    elif(ch==int(5)):
        my_chipher()
    elif(ch==int(6)):
        my_rsa()
    elif(ch==int(7)):
        my_rot13()
    elif(ch==int(8)):
        main()
    elif(ch==int(9)):
        my_Watermark()
    elif(ch==int(10)):
        my_Audio()
    else:
        break;
