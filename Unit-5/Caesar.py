# A Caesar cipher is a substitution cipher based on the idea of shifting each letter of a message by a fixed number of positions in the alphabet. This shift value is called the key. 

# In a module called Caesar.py, write the main function (i.e. called main()) that asks the user for a file with a message and key, then asks the user for a new file where the Caesar cipher encoded message and decoding key are saved. You may assume that the message to encode is on the first line of the text file and the key is on the second line. Similarly, your output file should have the encoded message on the first line and the decoding key on the second line. (Notice that you can check your program by inputting the file with the encoded message to make sure you get the original message back!)

# Rather than using Unicode, you should define your own alphabet that the encryption scheme can circle through. Your alphabet should include all lower and upper case letters and the space character – you may also include any punctuation you choose.

# You should have enough comments to match your pseudocode from the design phase of your development.


from os import write


def main():
    # Ask user for input file
    # Cesar cipher using custom alphabet
    user_input = input("Enter the name of the input file: ")
    print("Enter the name of the output file: ")
    key = int(input());
     
