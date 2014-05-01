MHKC
====

Disclaimer: This project is mostly done with a few experimental things thrown in and some flashy things added for presentation purposes, both of which can be safely ignored, extended or removed.


This project implements the Merkle-Hellman Knapsack Cryptosystem (MHKC) and basis reduction techniques to break it.


It provides a GUI client interface to communicate with other connected clients through a multicast server.
It also provides a console interface for breaking the MHKC encrypted ciphers when the public key is known.


Important: 
 1. Make sure the public key (if changed) is known to the console breaking interface.
 2. If you want to change the private key, make sure it is superincreasing and then add it to MHKC_Encryption.java. Then make sure you uncomment the lines to generate an encryption rule and a new public key. Finally, add this public key, modulo, sum and multiplier to MHKC_Encryption.java.
 3. If you want to change just the public key, then uncomment the lines to generate an encryption rule and a new public key. Then add this public key, modulo, sum and multiplier to MHKC_Encryption.java.


To run this project:
 1. First launch the server then as many clients as you want (change the host address if necessary).
 2. Next, observe the encrypted ciphertexts being displayed on the server console interface.
 3. If so desired, run the BreakMHKCConsole and paste in any of these ciphertexts from the server.
 4. The ciphertext should (but not always) be broken and the detailed output can then be examined.


Algorithms Implemented (and why):
 1. MHKC Encryption / Decryption to implement the cryptosystem.
 2. Gram-Schmidt Orthonormalization (GSO) to make all vectors mutually orthogonal and all of unit length.
 3. Lenstra-Lenstra-Lovász (LLL) Algorithm for lattice basis reduction, this calls GSO.
 4. Weight Reduction (WR) Algorithm for further reducing the weight of the basis.
 5. Kreher-Radziszowski (KR) Algorithm which is a superior method of reduction compared to using either LLL or WR alone.


API:
 1. Open the doc/ folder.
 2. Then open the index.html file using your browser of choice.
 3. This should show the JavaDoc API documentation generated from the doc comments in the source code.
 4. Of note is that this is probably out of date and should be rerun as necessary.


References:
 1. Chapter 8 of "Combinatorial Algorithms: Generation, Enumeration, and Search" by Donald L. Kreher and Douglas R. Stinson


If you have any questions or difficulties please make an issue report at https://github.com/wazy/mhkc/issues and I will get back to you as soon as possible.
