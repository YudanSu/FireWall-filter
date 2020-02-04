# Firewall-filter

## Description
For this project, 
1. We create the FirewallRules object, which contains direction (String), protocol (String), lowerBoundPort (int), upperBoundPort (int), lowerboundIPAddress (long), upperBoundIPAddress (long).
In the class. We also provide public method ipToLong() to convert IP address to long number. By doing so, the IP address can be compared as a number.

2. We create the Firewall class. 
   1. We create HashMap as data member.
   2. When the constructor load the file into lines.
We combine direction and protocol as key and create ArrayList of FirewallRules object as value.
The reason for doing so is we can reduce the running times of checking direction and protocol. 
   3. In the acceptPacket() method. 
      1. We can search direction-protocal key in the map by O(1) time complexity. 
      2. If it exist, we will check whether the port and IP address is in the range or not. Only if all the requirement are meet then it will return true otherwise it will return false.

## Space and Time Complexity
* Space: `O(n)`, n is the input size of file.
* Time: In the constructor. it takes `O(n)` to construct the HashMap.
In the acceptPacket() method, Worst case it should be `O(n)`. 
But we expect it to be fast (small constant in `O(n)`) since we devide the rules into several groups keyed by direction-protocol.      
  