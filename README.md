# Final Project: Automation Test Framework

Automation test framework untuk Web UI dan API testing menggunakan
Java, Selenium, Cucumber, dan REST Assured.

---

## Tech Stack

| Tool | Version | Kegunaan |
|------|---------|----------|
| Java | 17 | Bahasa pemrograman |
| Selenium WebDriver | 4.21.0 | Browser automation |
| Cucumber JVM | 7.18.0 | BDD framework |
| REST Assured | 5.4.0 | API testing |
| JUnit | 4.13.2 | Test runner |
| WebDriverManager | 5.9.2 | Auto driver setup |
| Gradle | 8.x | Build tool |

---

## Project Structure
```
final-project-automation/
├── .github/workflows/main.yml     # CI/CD GitHub Actions
├── src/test/
│   ├── java/org/automation/
│   │   ├── api/
│   │   │   ├── pages/             # API client layer
│   │   │   └── steps/             # API step definitions
│   │   ├── web/
│   │   │   ├── pages/             # Page Object Model
│   │   │   └── steps/             # Web step definitions
│   │   └── runner/                # Test runners
│   └── resources/features/
│       ├── api/                   # API feature files
│       └── web/                   # Web feature files
└── README.md
```

---

## Target Applications

- **Web UI**: https://www.demoblaze.com
- **API**: https://dummyapi.io/data/v1

---

## How to Run

### Jalankan semua test
```bash
./gradlew test
```

### Jalankan API test saja
```bash
./gradlew apiTest
```

### Jalankan Web UI test saja
```bash
./gradlew webTest
```

---

## Test Reports

Setelah test selesai, laporan tersedia di:
- API Report: `target/reports/api-report.html`
- Web Report: `target/reports/web-report.html`

---

## GitHub Actions

Workflow berjalan otomatis saat:
- **Manual trigger** — klik Run workflow di tab Actions GitHub
- **Pull Request** — setiap PR ke branch main