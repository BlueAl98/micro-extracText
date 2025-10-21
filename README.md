
# 📄 MicroExtracText

A lightweight **Java Spring Boot microservice** for extracting text from documents. Supports `.txt`, `.docx`, and `.pdf` files. Designed for easy integration in microservice architectures or standalone use.

---

## 🚀 Features

- Extract text from:
  - Plain text files (`.txt`)
  - Microsoft Word documents (`.docx`)
  - PDF documents (`.pdf`)
- Structured JSON responses
- Handles multipart file uploads
- Global exception handling for improved reliability

---

## 🏗 Project Structure

```
src/
├── main/
│   ├── java/com/blue/micro_extracText/
│   │   ├── MicroExtracTextApplication.java       # Main application entry
│   │   ├── configuration/GlobalExceptionHandler.java  # Global error handling
│   │   ├── controller/DocumentController.java   # REST API controller
│   │   ├── model/
│   │   │   ├── ApiResponse.java                 # Standard API response
│   │   │   └── DocumentResponse.java            # Document extraction result
│   │   └── service/DocumentTextExtractorService.java  # Core text extraction logic
└── test/java/com/blue/micro_extracText/
    └── MicroExtracTextApplicationTests.java     # Basic Spring Boot context tests
```

---

## 🛠 Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot
- **Libraries:**
  - Apache POI (for `.docx` support)
  - Apache PDFBox (for `.pdf` support)
  - Spring Web & Spring Boot Starter
- **Build Tool:** Maven

---

## 📥 API Endpoints

### `POST /documents`

Upload a document and get extracted text.

**Request:**

```http
POST /documents
Content-Type: multipart/form-data
```

- `file` — file to extract text from (`.txt`, `.docx`, `.pdf`)

**Response (Success):**

```json
{
  "status": 200,
  "message": "Text extracted successfully",
  "data": {
    "fileName": "example.pdf",
    "extractedText": "This is the content of the document...",
    "fileSize": 12345
  }
}
```

**Response (Error):**

```json
{
  "status": 400,
  "message": "Failed to extract text",
  "error": "Unsupported file type. Allowed: .txt, .docx, .pdf"
}
```

---

## ⚡ Getting Started

### Prerequisites

- Java 17+
- Maven
- Git

### Run Locally

```bash
# Clone the repository
git clone https://github.com/your-username/microextractext.git
cd microextractext

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The service will be available at `http://localhost:8080`.

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 📂 Example Usage (cURL)

```bash
curl -X POST "http://localhost:8080/documents"   -H "Content-Type: multipart/form-data"   -F "file=@/path/to/your/document.pdf"
```

---

## 📜 License

MIT License © 2025

---

## ❤️ Contributing

Contributions are welcome! Open issues or pull requests for improvements.

---

## 🤝 Contact

Created by **Alex Loera**  
GitHub: [https://github.com/your-username](https://github.com/your-username)
