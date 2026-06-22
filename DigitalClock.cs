// DigitalClock.cs - Цифровые часы на C# (WinForms)
using System;
using System.Drawing;
using System.Windows.Forms;

public class DigitalClock : Form
{
    private Label timeLabel, dateLabel;
    private bool format24 = true;
    private bool showSeconds = true;
    private Color textColor = Color.Lime;
    private Color bgColor = Color.Black;
    private Timer timer;

    public DigitalClock()
    {
        Text = "🕐 DigitForge - C#";
        Size = new Size(500, 350);
        StartPosition = FormStartPosition.CenterScreen;
        BackColor = bgColor;

        timeLabel = new Label()
        {
            Font = new Font("Courier New", 64, FontStyle.Bold),
            ForeColor = textColor,
            BackColor = bgColor,
            Dock = DockStyle.Top,
            Height = 120,
            TextAlign = ContentAlignment.MiddleCenter
        };
        Controls.Add(timeLabel);

        dateLabel = new Label()
        {
            Font = new Font("Arial", 18, FontStyle.Regular),
            ForeColor = textColor,
            BackColor = bgColor,
            Dock = DockStyle.Top,
            Height = 40,
            TextAlign = ContentAlignment.MiddleCenter
        };
        Controls.Add(dateLabel);

        FlowLayoutPanel controlPanel = new FlowLayoutPanel()
        {
            Dock = DockStyle.Top,
            Height = 50,
            BackColor = bgColor
        };
        Button formatBtn = new Button() { Text = "12/24 ч", Width = 80 };
        formatBtn.Click += (s, e) => { format24 = !format24; };
        controlPanel.Controls.Add(formatBtn);

        Button secondsBtn = new Button() { Text = "Секунды", Width = 80 };
        secondsBtn.Click += (s, e) => { showSeconds = !showSeconds; };
        controlPanel.Controls.Add(secondsBtn);

        Button colorBtn = new Button() { Text = "Цвет текста", Width = 100 };
        colorBtn.Click += (s, e) => {
            ColorDialog cd = new ColorDialog();
            if (cd.ShowDialog() == DialogResult.OK) { textColor = cd.Color; ApplyColors(); }
        };
        controlPanel.Controls.Add(colorBtn);

        Button bgBtn = new Button() { Text = "Цвет фона", Width = 100 };
        bgBtn.Click += (s, e) => {
            ColorDialog cd = new ColorDialog();
            if (cd.ShowDialog() == DialogResult.OK) { bgColor = cd.Color; ApplyColors(); }
        };
        controlPanel.Controls.Add(bgBtn);

        Controls.Add(controlPanel);

        timer = new Timer() { Interval = 1000 };
        timer.Tick += (s, e) => UpdateClock();
        timer.Start();
        UpdateClock();
    }

    private void UpdateClock()
    {
        DateTime now = DateTime.Now;
        string timeStr;
        if (format24)
            timeStr = now.ToString("HH:mm");
        else
            timeStr = now.ToString("hh:mm");
        if (showSeconds)
            timeStr += now.ToString(":ss");
        if (!format24)
            timeStr += now.ToString(" tt");
        timeLabel.Text = timeStr;

        string dateStr = now.ToString("dddd, d MMMM yyyy", new System.Globalization.CultureInfo("ru-RU"));
        dateLabel.Text = dateStr;
    }

    private void ApplyColors()
    {
        timeLabel.ForeColor = textColor;
        timeLabel.BackColor = bgColor;
        dateLabel.ForeColor = textColor;
        dateLabel.BackColor = bgColor;
        BackColor = bgColor;
        // обновить фон панели управления
        foreach (Control ctrl in Controls)
            if (ctrl is FlowLayoutPanel) ctrl.BackColor = bgColor;
    }

    [STAThread]
    static void Main() { Application.EnableVisualStyles(); Application.Run(new DigitalClock()); }
}
