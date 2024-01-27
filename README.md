# SimProc
![](https://github.com/tfchan-lab/simproc/blob/master/demo.jpg)

SimProc is a simple implementation of image processing filters.

## Introduction
This repository contains a simple command-line image processing tool designed to process uncompressed Portable Pixmap Format (`.ppm`) images.

## Installation
To get started with SimProc, you can choose one of the following methods:

1. **Download the latest release**: You can download the latest release from [release](https://github.com/tfchan-lab/simproc/releases/tag/v1.0). The release includes a zip folder containing images that you can use to test the program.
2. **Compile from source**: If you prefer, you can also compile the program from source using the latest JDK.

## Usage
Here are some of the commands you can use with SimProc:

- `simgproc ?` - Displays this message.
- `simgproc <filename> greyscale` - Converts the specified image to greyscale.
- `simgproc <filename> blur <size> <SD>` - Blurs the specified image.
- `simgproc <filename> downsample <multiplier>` - Down-samples the specified image.
- `simgproc <filename> sobel <threshold>` - Detects the edges of the specified image using the Sobel Operator.
- `simgproc <filename> canny <threshold_low> <threshold_high>` - Detects the edges of the specified image using the Canny Operator.
