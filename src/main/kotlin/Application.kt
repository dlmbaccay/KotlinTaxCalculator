import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.swing.*
import kotlin.math.floor

class Application : JFrame() {

    // tax variables
    private var monthlyIncome: Double = 0.0
    private var sss: Double = 0.0
    private var philHealth: Double = 0.0
    private var pagIBIG: Double = 0.0
    private var totalContribution: Double = 0.0
    private var incomeTax: Double = 0.0
    private var netAfterTax: Double = 0.0
    private var totalDeductions: Double = 0.0
    private var netAfterDeductions: Double = 0.0

    private val panel: JPanel = object : JPanel() {
        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            g.drawImage(ImageIcon("C:\\Users\\karla\\IdeaProjects\\KotlinTaxCalculator\\src\\main\\resources\\AppBG.png").image, 0, 0, null)
        }
    }

    // component variables
    private val inputField = JTextField()
    private val calculateButton = JButton()
    private val sssLabel = JLabel()
    private val philHealthLabel = JLabel()
    private val pagIBIGLabel = JLabel()
    private val totalConLabel = JLabel()
    private val incomeTaxLabel = JLabel()
    private val netATaxLabel = JLabel()
    private val totalDedLabel = JLabel()
    private val netADedLabel = JLabel()

    // design variables
    private val green = Color(49, 69, 40)
    private val formatter = NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat

    init { // constructor
        title = "Tax Calculator"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(700, 630)
        isResizable = false
        setLocationRelativeTo(null)

        formatter.applyPattern("#,##0.00")

        initComponents()
        updateLabels()

        panel.preferredSize = Dimension(700, 600)
        panel.layout = null // for setBounds() to work

        panel.add(inputField)
        panel.add(calculateButton)
        panel.add(sssLabel)
        panel.add(philHealthLabel)
        panel.add(pagIBIGLabel)
        panel.add(totalConLabel)
        panel.add(incomeTaxLabel)
        panel.add(netATaxLabel)
        panel.add(totalDedLabel)
        panel.add(netADedLabel)

        calculateButton.addActionListener {
            try {
                if (inputField.text.toDouble() < 0)
                    throw Exception("Positive values only!")
                else {
                    monthlyIncome = inputField.text.toDouble()
                    calculate()
                    updateLabels()
                }

            } catch (e: Exception) {
                JOptionPane.showMessageDialog(
                    panel,
                    "Input positive values only!",
                    "Input Error!",
                    JOptionPane.ERROR_MESSAGE
                )
                println("Positive values only!")
            }
        }

        add(panel)
        pack()
    }

    private fun initComponents() {
        inputField.setBounds(309, 125, 337, 33)
        inputField.foreground = green
        inputField.font = Font (Font.MONOSPACED, Font.BOLD, 20)

        val labelFont = Font(Font.MONOSPACED, Font.BOLD, 16)

        calculateButton.setBounds(290, 182, 120, 30)
        calculateButton.isContentAreaFilled = false

        sssLabel.setBounds(416, 284, 228, 24)
        sssLabel.foreground = green
        sssLabel.font = labelFont

        philHealthLabel.setBounds(416, 359, 228, 24)
        philHealthLabel.foreground = green
        philHealthLabel.font = labelFont

        pagIBIGLabel.setBounds(416, 434, 228, 24)
        pagIBIGLabel.foreground = green
        pagIBIGLabel.font = labelFont

        totalConLabel.setBounds(416, 508, 228, 24)
        totalConLabel.foreground = green
        totalConLabel.font = labelFont

        incomeTaxLabel.setBounds(56, 284, 228, 24)
        incomeTaxLabel.foreground = green
        incomeTaxLabel.font = labelFont

        netATaxLabel.setBounds(56, 359, 228, 24)
        netATaxLabel.foreground = green
        netATaxLabel.font = labelFont

        totalDedLabel.setBounds(56, 434, 228, 24)
        totalDedLabel.foreground = green
        totalDedLabel.font = labelFont

        netADedLabel.setBounds(56, 508, 228, 24)
        netADedLabel.foreground = green
        netADedLabel.font = labelFont
    }

    private fun updateLabels() {
        sssLabel.text = "₱ ${formatter.format(sss)}"
        philHealthLabel.text = "₱ ${formatter.format(philHealth)}"
        pagIBIGLabel.text = "₱ ${formatter.format(pagIBIG)}"
        totalConLabel.text = "₱ ${formatter.format(totalContribution)}"
        incomeTaxLabel.text = "₱ ${formatter.format(incomeTax)}"
        netATaxLabel.text = "₱ ${formatter.format(netAfterTax)}"
        totalDedLabel.text = "₱ ${formatter.format(totalDeductions)}"
        netADedLabel.text = "₱ ${formatter.format(netAfterDeductions)}"
    }

    private fun calculate() {
        sss = sssCompute(monthlyIncome)
        philHealth = philhealthCompute(monthlyIncome)
        pagIBIG = pagibigCompute(monthlyIncome)
        totalContribution = sss + philHealth + pagIBIG
        incomeTax = incomeTaxCompute(monthlyIncome - totalContribution)
        netAfterTax = (monthlyIncome - incomeTax)
        totalDeductions = (incomeTax + totalContribution)
        netAfterDeductions = (monthlyIncome - totalDeductions)
    }

    private fun sssCompute(monthlyIncome: Double): Double {
        return when (monthlyIncome) {
            in 0.0..4250.0 -> 180.0
            in 4251.0..29750.0 -> 180.0 + floor((monthlyIncome - 4250) / 500 + 1) * 22.50
            else -> 1350.00
        }
    }

    private fun philhealthCompute(monthlyIncome: Double): Double {
        return when (monthlyIncome) {
            in 0.0..10000.0 -> 225.0
            in 10_000.01..89999.99 -> monthlyIncome * 0.0225
            else -> 4050.00
        }
    }

    private fun pagibigCompute(monthlyIncome: Double): Double {
        val value: Double = if (monthlyIncome > 5_000) 5000.0 else monthlyIncome
        return if (value > 1500) value * 0.02 else value * 0.01
    }

    private fun incomeTaxCompute(deductedIncome: Double): Double {
        return when (deductedIncome) {
            in 0.0..20832.0 -> 0.0
            in 20833.0..33332.0 -> 0.0 + ((deductedIncome - 20833) * 0.15)
            in 33333.0..66666.0 -> 1875.00 + ((deductedIncome - 33333) * 0.20)
            in 66667.0..166666.0 -> 8541.80 + ((deductedIncome - 66667) * 0.25)
            in 166667.0..666666.0 -> 33541.80 + ((deductedIncome - 166667) * 0.30)
            else -> 183541.80 + ((deductedIncome - 666667) * 0.35)
        }
    }
}

fun main() {
    SwingUtilities.invokeLater { // to ensure that the GUI is created and displayed on the event dispatch thread
        val application = Application()
        application.isVisible = true
    }
}