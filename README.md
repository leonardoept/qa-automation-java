# 🧪 QA Automation (Java) – Selenium + TestNG + RestAssured

[![Tests](https://github.com/leonardoept/qa-automation-java/actions/workflows/tests.yml/badge.svg)](https://github.com/leonardoept/qa-automation-java/actions/workflows/tests.yml)

Framework de pruebas automatizadas en Java sobre [SauceDemo](https://www.saucedemo.com/) (UI) y [Restful Booker](https://restful-booker.herokuapp.com/) (API).
Es la versión en Java de mi [portfolio en Python con Playwright](https://github.com/leonardoept/qa-automation-portfolio).

## 🛠️ Tecnologías
- **Java 21** + **Maven**
- **Selenium WebDriver** – automatización de UI
- **TestNG** – ejecución, `@DataProvider` y dependencias entre tests
- **RestAssured** – pruebas de API (given / when / then)

## ✅ Casos automatizados
| Test | Tipo | Qué valida |
|---|---|---|
| `loginExitoso` | UI positivo | Login válido redirige a productos |
| `loginFallido` (×4) | UI negativo | Usuario bloqueado, contraseña incorrecta, campos vacíos |
| `listarReservas` | API | GET /booking devuelve 200 y una lista |
| `crearReserva` | API | POST /booking crea la reserva con los datos enviados |
| `consultarReservaCreada` | API | GET /booking/{id} devuelve la reserva creada |
| `reservaInexistenteDevuelve404` | API negativo | Un id inexistente devuelve 404 |

## 📁 Estructura
```
src/test/java/com/leonardo/
├── base/BaseTest.java       # abre y cierra el navegador
├── pages/LoginPage.java     # Page Object
├── tests/LoginTest.java     # tests de UI
└── api/BookingApiTest.java  # tests de API
```

## ▶️ Cómo ejecutarlo
Requiere JDK 21, Maven y Chrome.
```bash
mvn test
```

## 💡 Buenas prácticas aplicadas
- Page Object Model con localizadores privados
- Esperas explícitas (`WebDriverWait`), sin `Thread.sleep`
- Tests parametrizados con `@DataProvider`
- Encadenamiento de tests de API con `dependsOnMethods`

## 👤 Autor
**Leonardo Pérez** – QA Engineer | Córdoba, Argentina

[LinkedIn](https://www.linkedin.com/in/leonardopereztesterqa)