ARM-GPIO_Controller
===================

A Maven Java library used to access and modify easily the GPIO pins in different singleboard ARM machines.

The goal of the project is to provide access of the GPIO of different machines in the same way.
Another goal of the project is the ability to be extensible to add easily support of anothers machines.

The project starts providing supports for boards that exports pin with the standard linux mode (ex. Raspberry PI), and for AllWinner based board that use Sun4i version (ex. Cubieboard).

The project now support ONLY digital pins (only 1 or 0 values).
