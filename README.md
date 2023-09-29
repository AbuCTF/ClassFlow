# ClassFlow
# ClassFlow - QR Based Attendance Tracking App

ClassFlow is a user-friendly Android app designed to simplify attendance tracking in educational institutions and events using QR codes. With ClassFlow, you can easily create and manage attendance sessions, allowing participants to check in quickly and efficiently. This README provides detailed information about the app, including features, installation, usage, and customization.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Customization](#customization)
- [Contributing](#contributing)
- [License](#license)

## Features

ClassFlow offers a range of powerful features to streamline attendance tracking:

- **QR Code Generation:** Easily generate QR codes for attendance sessions.
- **Scanning:** Participants can scan QR codes with their Android devices to mark attendance.
- **User Management:** Manage and maintain a list of participants or students.
- **Attendance Reports:** View and export attendance reports for each session.
- **User Profiles:** Participants can create and manage their profiles.
- **Customization:** Customize the app to fit your organization's branding.

## Installation

### Prerequisites

- Android Studio
- Android Device or Emulator
- java 11 LTS
- netbeans IDE
- zxing 3.5.2
- postgreSQL 42.6.0
- bcrypt 0.4
- passwdqc 2.0.3 (optional)
- cygwin 3.4.9 (optional)

### Steps

1. Clone the repository or download the app's source code from [GitHub](https://github.com/Gaoh19/ClassFlow.git).
2. Open the project in Android Studio.
3. Build and run the app on your Android device or emulator.

## Usage

1. **User Registration and Login:**
   - Users can create accounts or log in to existing accounts.

2. **Session Creation:**
   - Create a new attendance session and set session details.
   - Generate a QR code for the session.

3. **Attendance Scanning:**
   - Participants can launch the app and scan the session's QR code.
   - Their attendance is marked and recorded.

4. **View Reports:**
   - Track attendance history and generate detailed reports for each session.

5. **Customization:**
   - Customize the app's appearance and branding to suit your organization's needs.

### Technologies and Tools Used

- **Android Studio:** ClassFlow is developed primarily using Android Studio, the official Integrated Development Environment (IDE) for Android app development.
- **Java:** The app's backend and functionality are implemented in Java, a versatile programming language for Android development.
- **ZXing (Zebra Crossing):** We utilize the ZXing library for QR code generation and scanning, making QR-based attendance tracking possible.
- **NetBeans IDE:** Some components of the project are developed using NetBeans IDE, especially for the server-side and web components.
- **Emulators:** During development and testing, Android emulators are used to simulate various Android devices and OS versions.
- **AWT (Abstract Window Toolkit):** Java's original GUI library with platform-dependent components
- **Swing:** modern and platform-independent GUI library that offers a richer set of customizable components
- **PostgreSQL:** powerful and versatile relational database management system 
- **pgAdmin4:** pgAdmin is a management tool for PostgreSQL and derivative relational databases such as EnterpriseDB's EDB Advanced Server
- **JDBC Driver:** JDBC (Java Database Connectivity) is a Java-based API that allows Java applications to interact with databases. PostgreSQL JDBC driver (often referred to as pgjdbc).
- **BCrypt:** jBCrypt is a Java™ implementation of OpenBSD's Blowfish password hashing code.
- **passwdqc:** passwdqc is a password/passphrase strength checking and policy enforcement toolset.
- **Cygwin:** Cygwin is a Unix-like environment for Windows including CLI.

## Customization

ClassFlow is designed to be highly customizable. You can tailor the app to your specific requirements in the following ways:

- **Branding:** Change the app's name, logo, and color scheme to match your organization's branding.
- **Localization:** Customize the app for different languages and regions.
- **Features:** Extend or modify the app's functionality to fit your unique use case.

## References

- [ChatGPT by OpenAI](https://www.openai.com/research/chatgpt)
  - The GOAT of Artificial Intelligence.

- [For Dummies](https://deadgawk.notion.site/ClassFlow-QR-Based-Attendance-Tracking-App-7e6b84cc3b3948cb847678906e833c94?pvs=4)
  - Like the name suggests.

- [PostgreSQL Commands](https://deadgawk.notion.site/PostgreSQL-Commands-c190b7c5408347fe99e494f2545c020a?pvs=4)
  - Takes you to a Notion article that explains briefly about the various commands used in Postgre and Sequel.

- [GitHub - Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
  - GitHub's guide on mastering Markdown was a valuable resource for understanding Markdown syntax.

- [BCrypt](https://www.mindrot.org/projects/jBCrypt/)
  - jBCrypt is a Java™ implementation of OpenBSD's Blowfish password hashing code.

- [passwdqc](https://www.openwall.com/passwdqc/)
  - passwdqc is a password/passphrase strength checking and policy enforcement toolset.

## Contributing

We welcome contributions from the community to make ClassFlow even better. To contribute:

1. Fork the repository.
2. Create a new branch for your changes.
3. Make your changes and commit them.
4. Submit a pull request to the main repository.

## License

This app is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues, have questions, or need assistance, please [open an issue](https://github.com/yourrepository/classflow/issues) on GitHub.

---

Thank you for choosing ClassFlow to simplify attendance tracking! We hope it enhances your educational or event management experience. If you have any feedback or suggestions, we'd love to hear from you.
