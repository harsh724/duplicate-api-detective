🕵️‍♂️ Duplicate API Detective
Duplicate API Detective is a developer tool that scans your codebase or API specifications to detect duplicate, redundant, or overlapping API endpoints.
It helps teams maintain clean, well-organized APIs by identifying potential collisions or inconsistencies across microservices or within a single service.

🚀 Features


🔍 Detect duplicate endpoints across multiple files or repositories


🧠 Smart matching based on path patterns, HTTP methods, and parameters


⚡ Fast scanning — handles large codebases efficiently


📊 Detailed reports highlighting duplicates and conflicts


🛠️ Configurable — supports custom ignore patterns and rules


🧩 Supports REST and OpenAPI (Swagger) formats



🏗️ Project Structure
```duplicate-api-detective/
├── src/
│   ├── main/
│   │   ├── java/com/example/duplicateapidet/
│   │   │   ├── detectors/       # Core duplicate detection logic
│   │   │   ├── models/          # API models and DTOs
│   │   │   ├── utils/           # File parsing & normalization utils
│   │   │   └── DuplicateApiDetective.java
│   └── test/
│       └── java/...             # Unit and integration tests
├── resources/
│   ├── sample-api-specs/        # Example input files
│   └── config.yaml              # Default configuration
├── pom.xml                      # Maven build file
└── README.md
```

⚙️ Installation
Prerequisites


Java 17+


Maven 3.8+


(Optional) Docker if you want to run it containerized


Clone & Build
git clone https://github.com/<your-username>/duplicate-api-detective.git
cd duplicate-api-detective
mvn clean package


🧩 Usage
1️⃣ Command-line
Run directly from the packaged JAR:
java -jar target/duplicate-api-detective.jar \
  --input ./api-specs \
  --output ./report.json

Parameters:
FlagDescription--inputPath to directory or file containing API specs or source code--outputPath to save the generated report (JSON/HTML)--configOptional: custom config file path--formatOptional: json or html output
2️⃣ Example
java -jar duplicate-api-detective.jar --input ./services --output report.html


📊 Example Output
Duplicate API Report (Summary)
EndpointHTTP MethodOccurrencesFiles/users/{id}GET3user-service, profile-service, legacy-api/ordersPOST2order-service, checkout-service

⚙️ Configuration Example
config.yaml
ignorePaths:
  - "/health"
  - "/metrics"
caseSensitive: false
outputFormat: "json"


🧪 Running Tests
mvn test


🐳 Run with Docker
docker build -t duplicate-api-detective .
docker run -v $(pwd)/api-specs:/app/api-specs duplicate-api-detective


🧠 Roadmap


 Support for GraphQL and gRPC


 Integration with CI/CD pipelines


 VS Code plugin for instant feedback


 Enhanced HTML reporting



🤝 Contributing
Contributions are welcome!
Please open an issue or submit a pull request.


Fork the repo


Create your feature branch (git checkout -b feature/new-detection)


Commit your changes (git commit -m 'Add new feature')


Push to the branch (git push origin feature/new-detection)


Open a Pull Request 🎉



📄 License
This project is licensed under the MIT License.

💡 Inspiration
Built to help teams detect redundant endpoints early and improve API governance —
because consistency is the first step to great developer experience.

Would you like me to make a shorter, cleaner version (for a public repo) or keep this detailed technical one (for dev teams/contributors)?
