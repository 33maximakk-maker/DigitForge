DigitForge — Цифровые часы на 7 языках
DigitForge — коллекция из семи независимых реализаций цифровых часов с расширенными возможностями. Каждая версия работает на своём языке программирования и предлагает настраиваемый интерфейс с отображением времени, даты, дня недели, выбором формата (12/24 ч), отображением секунд и сменой цветовой схемы.

✨ Общие возможности
🕒 Отображение текущего времени (часы:минуты:секунды) с обновлением каждую секунду

📅 Отображение даты (день, месяц, год) и дня недели

🔄 Переключение формата 12-часовой / 24-часовой (AM/PM в 12-часовом)

🎨 Настройка цвета текста и фона (ввод HEX или выбор из палитры)

⏱️ Показ/скрытие секунд (переключатель)

🌐 Интерфейсы:

Десктопные GUI: Python (Tkinter), Java (Swing), C# (WinForms)

Веб-приложения: JavaScript (HTML+CSS+JS), Go, Rust, PHP (сервер + клиент)

📋 Сравнение реализаций
Язык	Интерфейс	Формат 12/24	Секунды	Дата	Смена цвета	Автообновление
Python	Tkinter GUI	✅	✅	✅	✅ (кнопки)	✅ (after)
JavaScript	Веб (HTML+CSS)	✅	✅	✅	✅ (input)	✅ (setInterval)
Go	Веб (сервер)	✅ (клиент)	✅	✅	✅ (клиент)	✅ (клиент)
Rust	Веб (сервер)	✅ (клиент)	✅	✅	✅ (клиент)	✅ (клиент)
Java	Swing GUI	✅	✅	✅	✅ (кнопки)	✅ (Timer)
C#	WinForms GUI	✅	✅	✅	✅ (кнопки)	✅ (Timer)
PHP	Веб (сервер)	✅ (клиент)	✅	✅	✅ (клиент)	✅ (клиент)
🚀 Быстрый старт
Python
bash
# Tkinter встроен
python digital_clock.py
JavaScript (браузер)
Откройте digital_clock.html в браузере.

Go
bash
go run digital_clock.go
# Откройте http://localhost:8080
Rust
bash
cargo run
# Откройте http://localhost:8000
Java
bash
javac DigitalClock.java && java DigitalClock
C#
bash
csc /reference:System.Windows.Forms.dll /reference:System.Drawing.dll DigitalClock.cs
DigitalClock.exe
PHP
bash
php -S localhost:8000
# Откройте http://localhost:8000/digital_clock.php
