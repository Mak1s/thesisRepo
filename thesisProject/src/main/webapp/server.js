const express = require('express');
const multer = require('multer');
const fs = require('fs');
const path = require('path');

const app = express();
const port = 3000;

// Set up multer for file uploads
const upload = multer({ dest: 'uploads/' });

// Endpoint to handle file upload
app.post('/upload', upload.single('fileUpload'), (req, res) => {
  const file = req.file;
  if (!file) {
    return res.status(400).send('No file uploaded.');
  }

  // Read the contents of the file
  fs.readFile(file.path, 'utf8', (err, data) => {
    if (err) {
      return res.status(500).send('Error reading file.');
    }

    // Process the file contents as needed
    console.log('File contents:', data);

    // Respond with the file contents or any other response
    res.json({ message: 'File uploaded successfully', contents: data });

    // Optionally, delete the file after processing
    fs.unlink(file.path, (err) => {
      if (err) console.error('Error deleting file:', err);
    });
  });
});

// Serve the HTML file (optional, for demonstration purposes)
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
