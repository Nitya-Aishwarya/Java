# File Handling in Java and Spring Boot

File handling means working with files through code. A file can be a text file, CSV file, Excel file, PDF file, image file, or any other document. When we say “file handling,” we usually mean creating a file, reading a file, writing into a file, uploading a file, downloading a file, extracting data from a file, validating a file, and saving the file somewhere.

A file may look simple to us, but internally every file is stored as bytes. A computer does not directly understand “PDF,” “Excel,” or “text.” It understands binary data, which means zeros and ones. So, when we handle a file in Java, we are actually handling bytes and then converting those bytes into meaningful content.

For example, a text file may contain this:

```text
Hello Nitya
Welcome to Java file handling
```

But internally, the computer stores this text as bytes. Java reads those bytes and converts them into characters so that humans can understand the content.

# What Is a File?

A file is a container of data. It stores information permanently on disk. For example, `notes.txt` stores text, `employees.csv` stores comma-separated values, `report.pdf` stores PDF content, and `students.xlsx` stores Excel data.

Different files have different internal structures. A text file is easy to read because it contains plain characters. A CSV file is also a text file, but the data is arranged using commas. An Excel file is more complex because it contains sheets, rows, columns, formatting, and sometimes formulas. A PDF file is even more complex because it stores text, images, fonts, positions, and layout information.

Because every file type is structured differently, we use different techniques or libraries to extract content from them.

# What Is File Upload?

File upload means sending a file from the user’s computer to the server. In a Spring Boot application, the user selects a file from the browser or Postman, and the frontend sends that file to the backend through an HTTP request.

The request usually uses a format called `multipart/form-data`. This format allows the request to carry normal data and file data together.

For example, when a user uploads `employees.csv`, the browser sends the file bytes to Spring Boot. Spring Boot receives the uploaded file as a `MultipartFile`.

# What Is MultipartFile?

`MultipartFile` is a Spring Boot object that represents an uploaded file.

It is not exactly the file itself. It is a Java object that gives us access to the uploaded file’s name, size, content type, bytes, and input stream.

For example:

```java
@PostMapping("/upload")
public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok("File received");
}
```

In this code, `@RequestParam("file")` tells Spring Boot to take the uploaded file from the HTTP request. The `MultipartFile file` variable holds that uploaded file.

`MultipartFile` gives useful methods like:

```java
file.getOriginalFilename();
file.getSize();
file.getContentType();
file.getBytes();
file.getInputStream();
file.isEmpty();
```

`getOriginalFilename()` gives the uploaded file name.
`getSize()` gives the file size in bytes.
`getContentType()` gives the file type, such as `text/plain`, `application/pdf`, or Excel MIME type.
`getBytes()` loads the entire file into memory.
`getInputStream()` allows us to read the file gradually.
`isEmpty()` checks whether the uploaded file is empty.

# What Is InputStream?

`InputStream` is used to read data from a file as a stream of bytes.

A stream means data flows little by little. Imagine water flowing through a pipe. You do not carry the entire lake at once. You take water as it flows. Similarly, `InputStream` reads file data gradually.

This is useful when files are large. If we use `file.getBytes()`, the entire file is loaded into memory. This is fine for small files, but it can be dangerous for large files. For large files, `InputStream` is better because it reads the file step by step.

Example:

```java
InputStream inputStream = file.getInputStream();
```

This line opens a stream from the uploaded file.

# What Is BufferedReader?

`BufferedReader` is used to read text efficiently, usually line by line.

It is mostly used for text files, CSV files, and log files.

The word “buffered” means it uses a temporary memory area called a buffer. Instead of reading one character at a time from disk, it reads a larger chunk into memory and then gives the data to the program. This makes reading faster.

Example:

```java
BufferedReader reader = new BufferedReader(
        new InputStreamReader(file.getInputStream())
);
```

This code has three layers.

First, `file.getInputStream()` reads raw bytes from the uploaded file.

Second, `InputStreamReader` converts those bytes into characters.

Third, `BufferedReader` reads those characters efficiently line by line.

The most important method is:

```java
reader.readLine();
```

This method reads one line from the file.

Example CSV file:

```csv
name,age,city
Ravi,21,Chennai
Meena,22,Bangalore
```

Code:

```java
String line;

while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
```

This loop reads one line at a time until the file ends. When there are no more lines, `readLine()` returns `null`.

# Reading a CSV File

A CSV file is a text file where values are separated by commas.

Example:

```csv
name,age,city
Ravi,21,Chennai
Meena,22,Bangalore
Arun,23,Hyderabad
```

Each row represents one record. Each comma separates columns.

To extract the data, we read the file line by line and split each line using a comma.

```java
public List<String[]> extractCsv(MultipartFile file) throws Exception {

    List<String[]> rows = new ArrayList<>();

    BufferedReader reader = new BufferedReader(
            new InputStreamReader(file.getInputStream())
    );

    String line;

    while ((line = reader.readLine()) != null) {
        String[] columns = line.split(",");
        rows.add(columns);
    }

    return rows;
}
```

In this code, `rows` stores all extracted rows. The `BufferedReader` reads the file line by line. The `split(",")` method breaks each line into columns. Finally, each row is added to the list.

If the line is:

```text
Ravi,21,Chennai
```

then:

```java
line.split(",");
```

produces:

```text
["Ravi", "21", "Chennai"]
```

# Handling 2000 Rows

If a file has 2000 rows, we should not think of it as one big block of data. We should think of it as repeated rows.

The correct mental model is:

```text
Read row 1.
Process row 1.
Read row 2.
Process row 2.
Continue until row 2000.
```

This is why loops are important in file handling.

For CSV files, one `while` loop is enough because each row is one line.

For Excel files, we usually need nested loops because Excel has rows and cells.

# Reading an Excel File

An Excel file is not plain text. It has a structure.

The structure is:

```text
Workbook
  Sheet
    Row
      Cell
```

A workbook means the entire Excel file. A sheet means one page inside the Excel file. A row means one horizontal line of data. A cell means one box inside a row.

To read Excel files in Java, we use Apache POI.

Maven dependency:

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>
```

Code:

```java
public List<List<String>> extractExcel(MultipartFile file) throws Exception {

    List<List<String>> data = new ArrayList<>();

    Workbook workbook = WorkbookFactory.create(file.getInputStream());
    Sheet sheet = workbook.getSheetAt(0);

    for (Row row : sheet) {

        List<String> rowData = new ArrayList<>();

        for (Cell cell : row) {
            rowData.add(cell.toString());
        }

        data.add(rowData);
    }

    workbook.close();

    return data;
}
```

In this code, `WorkbookFactory.create()` opens the Excel file. `getSheetAt(0)` gets the first sheet. The outer loop reads each row. The inner loop reads each cell in that row. Each cell value is converted to a string and stored in `rowData`. Finally, each row is added to the main `data` list.

If the Excel file has 2000 rows, the outer loop runs 2000 times.

# Reading a Text File

A text file is the simplest file type.

Example:

```text
Hello
This is a text file
Java can read this easily
```

Code:

```java
public String extractTextFile(MultipartFile file) throws Exception {
    return new String(file.getBytes(), StandardCharsets.UTF_8);
}
```

This code reads all bytes from the file and converts them into a string using UTF-8 encoding.

This approach is fine for small text files. For large text files, `BufferedReader` is better.

# Reading a PDF File

A PDF file is not plain text. It contains layout, font information, positioning, and sometimes images. That is why we need a library such as Apache PDFBox.

Maven dependency:

```xml
<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>2.0.30</version>
</dependency>
```

Code:

```java
public String extractPdf(MultipartFile file) throws Exception {

    PDDocument document = PDDocument.load(file.getInputStream());

    PDFTextStripper stripper = new PDFTextStripper();

    String text = stripper.getText(document);

    document.close();

    return text;
}
```

`PDDocument.load()` opens the PDF document. `PDFTextStripper` extracts readable text from the PDF. `stripper.getText(document)` returns the extracted text. Finally, we close the document to release resources.

# Writing a File in Java

Writing means sending data from the program into a file.

Example:

```java
Path path = Path.of("notes.txt");

Files.writeString(path, "Hello from Java");
```

This creates a file called `notes.txt` and writes text into it.

If the file already exists, the content may be replaced.

# Appending to a File

Appending means adding new content without deleting existing content.

```java
Files.writeString(
        Path.of("notes.txt"),
        "\nNew line added",
        StandardOpenOption.APPEND
);
```

This adds a new line at the end of the file.

# Saving an Uploaded File

Sometimes we do not only extract content. We also save the uploaded file on the server.

```java
public String saveFile(MultipartFile file) throws Exception {

    Path uploadDir = Path.of("uploads");

    if (!Files.exists(uploadDir)) {
        Files.createDirectories(uploadDir);
    }

    Path filePath = uploadDir.resolve(file.getOriginalFilename());

    Files.copy(
            file.getInputStream(),
            filePath,
            StandardCopyOption.REPLACE_EXISTING
    );

    return filePath.toString();
}
```

This code creates an `uploads` folder if it does not exist. Then it creates a path for the uploaded file. Finally, it copies the uploaded file stream into that location.

# Validating Files

Validation is very important because users may upload invalid or dangerous files.

First, check whether the file is empty:

```java
if (file.isEmpty()) {
    throw new RuntimeException("File is empty");
}
```

Second, check the file size:

```java
if (file.getSize() > 5 * 1024 * 1024) {
    throw new RuntimeException("File is too large");
}
```

This rejects files larger than 5 MB.

Third, check the content type:

```java
if (!file.getContentType().equals("text/plain")) {
    throw new RuntimeException("Only text files are allowed");
}
```

This allows only text files.

Validation protects the server from bad input, huge files, wrong file formats, and possible security risks.

# Complete Spring Boot Example

Controller:

```java
@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<List<String[]>> uploadCsv(
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        List<String[]> data = fileService.extractCsv(file);

        return ResponseEntity.ok(data);
    }
}
```

Service:

```java
@Service
public class FileService {

    public List<String[]> extractCsv(MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        List<String[]> rows = new ArrayList<>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        );

        String line;

        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            rows.add(columns);
        }

        return rows;
    }
}
```

This code receives a CSV file, checks if it is empty, reads it line by line, splits each row into columns, stores all rows in a list, and returns the extracted data as the API response.

# Final Mental Model

File handling is not about memorizing code. It is about understanding the flow.

A file comes into the backend as bytes. Spring Boot wraps the uploaded file as a `MultipartFile`. We validate the file. Then we read the file using bytes, streams, or readers. After that, we extract meaningful data depending on the file type. Finally, we return the result, save it to disk, or store it in a database.

The full flow is:

```text
User uploads file
↓
Spring Boot receives MultipartFile
↓
Validate file
↓
Read file using InputStream or BufferedReader
↓
Extract text, rows, or cells
↓
Process the extracted data
↓
Return response or save to database
```

The most important sentence to remember is:

```text
File handling means receiving bytes, reading bytes, converting bytes into meaningful data, and then using that data.
```
