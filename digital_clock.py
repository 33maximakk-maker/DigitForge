# digital_clock.py - Цифровые часы на Python (Tkinter)
import tkinter as tk
from tkinter import colorchooser, ttk
import time
from datetime import datetime

class DigitalClock:
    def __init__(self, root):
        self.root = root
        self.root.title("🕐 DigitForge - Python")
        self.root.geometry("500x350")
        self.root.resizable(False, False)

        # Настройки
        self.format_24h = True
        self.show_seconds = True
        self.text_color = "#00ff00"
        self.bg_color = "#000000"

        # Основной фрейм
        self.frame = tk.Frame(root, bg=self.bg_color)
        self.frame.pack(fill=tk.BOTH, expand=True)

        # Метка времени
        self.time_label = tk.Label(self.frame, font=("Courier", 64, "bold"),
                                   fg=self.text_color, bg=self.bg_color)
        self.time_label.pack(pady=20)

        # Метка даты
        self.date_label = tk.Label(self.frame, font=("Arial", 16),
                                   fg=self.text_color, bg=self.bg_color)
        self.date_label.pack()

        # Панель управления
        control_frame = tk.Frame(self.frame, bg=self.bg_color)
        control_frame.pack(pady=15)

        # Кнопки настройки
        tk.Button(control_frame, text="12/24 ч", command=self.toggle_format,
                  bg="#2c3e50", fg="white").pack(side=tk.LEFT, padx=5)
        tk.Button(control_frame, text="Секунды", command=self.toggle_seconds,
                  bg="#2c3e50", fg="white").pack(side=tk.LEFT, padx=5)
        tk.Button(control_frame, text="Цвет текста", command=self.choose_text_color,
                  bg="#2c3e50", fg="white").pack(side=tk.LEFT, padx=5)
        tk.Button(control_frame, text="Цвет фона", command=self.choose_bg_color,
                  bg="#2c3e50", fg="white").pack(side=tk.LEFT, padx=5)

        # Обновление
        self.update_clock()

    def update_clock(self):
        now = datetime.now()
        # Время
        if self.format_24h:
            time_str = now.strftime("%H:%M")
        else:
            time_str = now.strftime("%I:%M")
        if self.show_seconds:
            time_str += now.strftime(":%S")
        if not self.format_24h:
            time_str += now.strftime(" %p")
        # Дата
        date_str = now.strftime("%A, %d %B %Y")
        self.time_label.config(text=time_str)
        self.date_label.config(text=date_str)
        self.root.after(1000, self.update_clock)

    def toggle_format(self):
        self.format_24h = not self.format_24h

    def toggle_seconds(self):
        self.show_seconds = not self.show_seconds

    def choose_text_color(self):
        color = colorchooser.askcolor(color=self.text_color)[1]
        if color:
            self.text_color = color
            self.time_label.config(fg=color)
            self.date_label.config(fg=color)

    def choose_bg_color(self):
        color = colorchooser.askcolor(color=self.bg_color)[1]
        if color:
            self.bg_color = color
            self.frame.config(bg=color)
            self.time_label.config(bg=color)
            self.date_label.config(bg=color)
            # также обновить фон кнопок, но для простоты оставим

if __name__ == "__main__":
    root = tk.Tk()
    app = DigitalClock(root)
    root.mainloop()
