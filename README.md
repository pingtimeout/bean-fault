# bean-fault
An very bad Unsafe migration path.

It is possible, using `sun.misc.Unsafe` to crash the JVM with a segmentation fault.  This project is a replacement for that "feature": it prints a "Segmentation fault" message and kills the JVM.

## Disclaimer
THIS CODE IS INTENDED TO CRASH A JVM, CAPSLOCK IS REQUIRED TO MAKE SURE THIS MESSAGE IS READABLE.
THERE IS NO WARRANTY FOR THE PROGRAM. IT MIGHT NOT WORK AND THEREFORE LET YOUR JVM LIVE, WHICH WOULD BE FORTUNATE, BUT ALSO A BUG.

## Howto

```java
new BeanFault().segfault();
```

## Licence
This code is licenced under the GNU General Public Licence Version 3.  See the `LICENSE` file.

## Special thanks

Thanks to Chris Engelbert ([noctarius2k on Twitter](https://twitter.com/noctarius2k)) for the idea and for the many (too many?) Unsafe discussions.

