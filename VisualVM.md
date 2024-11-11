# VisualVM Setup Guide

## 1. Download and Install VisualVM

### Windows:
1. Download VisualVM from: https://visualvm.github.io/download.html
2. Extract the ZIP file to a location (e.g., `C:\Program Files\visualvm`)
3. Add the bin directory to your PATH:
   ```
   C:\Program Files\visualvm\bin
   ```

### Mac:
```bash
brew install visualvm
```

### Linux:
```bash
# Ubuntu/Debian
sudo apt-get install visualvm

# Or download and extract manually:
wget https://github.com/visualvm/visualvm.github.io/releases/download/2.1.6/visualvm_216.zip
unzip visualvm_216.zip
```

## 2. Configure JDK for VisualVM

Make sure you have JDK installed (not just JRE). VisualVM needs tools.jar from the JDK.

## 3. Enable JMX Connection

Add these VM options to your IntelliJ run configuration:
```
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=9010
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
```

## 4. Verify Installation
Run from command line:
```bash
visualvm --version
```