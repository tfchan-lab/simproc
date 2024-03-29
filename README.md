# SimProc
![Original image source: https://wall.alphacoders.com/big.php?i=1090586](https://github.com/tfchan-lab/simproc/blob/master/demo.jpg)

SimProc is a simple implementation of image processing filters.

## Introduction
This repository contains a simple, lightweight command-line image processing tool designed to process uncompressed Portable Pixmap Format (`.ppm`) images. An experimental GUI tool is also in progress, basic functionality has been implemented but not yet production ready. See [changelog](https://github.com/tfchan-lab/simproc/blob/master/changelog.md).

## Installation
To get started with SimProc, you can choose one of the following methods:

1. **Download the latest release**: You can download the latest release from [release](https://github.com/tfchan-lab/simproc/releases/). The release includes a zip folder containing images that you can use to test the program.
2. **Compile from source**: If you prefer, you can also compile the program from source using the latest JDK.

## Usage
Here are some of the commands you can use with SimProc:

- `simproc-cli ?` - Displays this message.
- `simproc-cli greyscale <filename> ` - Converts the specified image to greyscale.
- `simproc-cli blur <filename> <size> <SD>` - Blurs the specified image.
- `simproc-cli sharpen <filename> <intensity>` - Sharpens the specified image.
- `simproc-cli downsample <filename> <multiplier>` - Down-samples the specified image.
- `simproc-cli sobel <filename> <threshold>` - Detects the edges of the specified image using the Sobel Operator.
- `simgproc-cli canny <filename> <threshold_low> <threshold_high>` - Detects the edges of the specified image using the Canny Operator.
  
Alternatively, you can try the latest GUI tool:
- `simproc-gui ?` - Displays this message.
- `simproc-gui <filename>` - Processes the specified image.
