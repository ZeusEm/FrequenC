# FrequenC 📡🔧  
*Control your Agile Frequency Transmitter with ease!*

[![License: GNU GPL v3](https://img.shields.io/badge/License-GNU%20GPL-blue)]([https://opensource.org/licenses/MIT](https://opensource.org/license/gpl-3-0))
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)](https://kotlinlang.org)

**FrequenC** is an Android app that communicates with the **Agile Frequency Transmitter** over **USB serial** to configure frequency and duty cycle parameters. Developed for engineers and technicians, it simplifies remote hardware tuning with a clean UI and robust error handling.

---

## 🚀 Features  
- **Frequency Bandwidth Tuning**: Set start (`F START`) and stop (`F STOP`) frequencies (MHz).  
- **Duty Cycle Adjustment**: Configure pulse width (`PW`) and pulse repetition interval (`PRI`) in microseconds (µs).  
- **Serial Communication**: USB-C/RS-422 interface with baud rate **9600**, **8N1** protocol.  
- **Command Validation**: Ensures ASCII command frames match the hardware’s required format (e.g., `AF B 3000 3500 v`).  
- **Real-time Feedback**: Toast messages and logs for success/error reporting.  

---

## 🛠️ Tech Stack  
- **Language**: Kotlin  
- **Libraries**:  
  - `usb-serial-for-android` for USB communication.  
  - `AndroidX AppCompat` for backward-compatible UI.  
- **Tools**: Android Studio, USB-to-RS422 Adapter.  

---

## 📦 Getting Started  
### Prerequisites  
- Android tablet with **USB Host Mode** support.  
- USB-to-RS422 adapter.  
- Agile Frequency Transmitter device.  

### Installation  
1. **Clone the Repository**:  
   ```bash  
   git clone https://github.com/ZeusEm/FrequenC.git
2.  **Open in Android Studio**: Import the project and sync Gradle dependencies.
3.  **Build the APK**:
    *   Build > Build Bundle(s) / APK(s) > Build APK.
    *   Transfer the APK to your android device and install.
        
🖥️ Usage
---------

1.  **Connect Hardware**:
    *   Plug the USB-to-RS422 adapter into your tablet.
    *   Connect the adapter to the Agile Frequency Transmitter.
2.  **Launch FrequenC**:
    *   Enter values for **F START**, **F STOP**, **PW**, and **PRI**.
3.  **Send Commands**:
    *   Tap **Send Frequency Tuning** or **Send Duty Cycle** to transmit data.

🛑 Troubleshooting
------------------

*   **App Crashes on Launch**:
    *   Ensure your theme uses Theme.AppCompat (see themes.xml).        
*   **USB Device Not Recognized**:
    *   Check USB permissions in AndroidManifest.xml.
    *   Verify baud rate matches the hardware (9600).
        
🤝 Contributing
---------------

We welcome contributions!
1.  Fork the repository.
2.  bashCopygit checkout -b feature/amazing-feature
3.  Commit your changes.
4.  Push to the branch and open a Pull Request.
    
📄 License
----------

Distributed under the **GNU GPL v3**. See [LICENSE](https://opensource.org/license/gpl-3-0) for details.

🙏 Acknowledgments
------------------

*   The usb-serial-for-android library for simplifying USB communication.
*   RS-422 protocol documentation.
    
Let’s make FrequenC the go-to tool for frequency engineers! 🎯🚀
