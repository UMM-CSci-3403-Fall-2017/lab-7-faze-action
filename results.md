 Results
-------------
Running 200 requests with pumpkins.jpeg:

- Without multithreading it took over 2 minutes and the server dropped some requests.

- When multithreading just server this took 28sec
  - with pool sizes of 16, 32, and 48
  - The time relative to number of requests goes up by about 1 second per 8 requests on multivac
  which has 8 cores
  - in general, multithreading in this way is about 4 times faster than not, which is unexpected 
  because intuitively it ought to be 8 times faster on multivac.
  
- When multithreading both the server and the client it took 16 seconds.
 - In general, this is twice as fast as not multithreading which makes sense because the reading
 and writing from the streams don't have to wait for other pair to finish first.

