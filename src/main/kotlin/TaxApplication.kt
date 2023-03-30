import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionListener
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.swing.*
import kotlin.math.floor

class TaxApplication : JFrame() {

    // tax computation variables
    private var monthlyIncome: Double = 0.0
    private var sss: Double = 0.0
    private var philHealth: Double = 0.0
    private var pagIBIG: Double = 0.0
    private var totalContribution: Double = 0.0
    private var incomeTax: Double = 0.0
    private var netAfterTax: Double = 0.0
    private var totalDeductions: Double = 0.0
    private var netAfterDeductions: Double = 0.0

    // GUI component variables
    private var panel = JPanel()
    private var garyNationTitle = JLabel()
    private val inputTitle = JLabel()
    private val inputField = JTextField()
    private val calculateButton = JButton()
    private val sssTitle = JLabel()
    private val sssLabel = JLabel()
    private val philHealthTitle = JLabel()
    private val philHealthLabel = JLabel()
    private val pagIBIGTitle = JLabel()
    private val pagIBIGLabel = JLabel()
    private val totalConTitle = JLabel()
    private val totalConLabel = JLabel()
    private val incomeTaxTitle = JLabel()
    private val incomeTaxLabel = JLabel()
    private val netATaxTitle = JLabel()
    private val netATaxLabel = JLabel()
    private val totalDedTitle = JLabel()
    private val totalDedLabel = JLabel()
    private val netADedTitle = JLabel()
    private val netADedLabel = JLabel()

    // design variables
    private val darkGreen = Color(49, 69, 40)
    private val lightGreen = Color(177,216,183)
    private val decimalFormat = NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat

    init { // constructor
        title = "Tax Calculator"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(700, 630)
        isResizable = false
        setLocationRelativeTo(null)

        // formatting of value printing
        decimalFormat.applyPattern("#,##0.00")

        // initialize display
        initComponents()
        updateLabels()

        // initialize panel
        panel.preferredSize = Dimension(700, 600)
        panel.isOpaque = true
        panel.background = lightGreen
        panel.layout = null // for setBounds() to work

        // add components to panel
        panel.add(garyNationTitle)
        panel.add(inputTitle)
        panel.add(inputField)
        panel.add(calculateButton)
        panel.add(sssTitle)
        panel.add(sssLabel)
        panel.add(philHealthTitle)
        panel.add(philHealthLabel)
        panel.add(pagIBIGTitle)
        panel.add(pagIBIGLabel)
        panel.add(totalConTitle)
        panel.add(totalConLabel)
        panel.add(incomeTaxTitle)
        panel.add(incomeTaxLabel)
        panel.add(netATaxTitle)
        panel.add(netATaxLabel)
        panel.add(totalDedTitle)
        panel.add(totalDedLabel)
        panel.add(netADedTitle)
        panel.add(netADedLabel)

        // get user prompt to calculate entered value
        inputField.addActionListener(inputListener())
        calculateButton.addActionListener(inputListener())

        add(panel) // add panel to frame
        pack()
    }

    private fun initComponents() {

        val titleFont = Font(Font.SANS_SERIF, Font.BOLD, 18)
        val labelFont = Font(Font.SANS_SERIF, Font.BOLD, 18)
        val labelBorder = BorderFactory.createLineBorder(darkGreen, 1)

        garyNationTitle.setBounds(0, 0, 700, 78)
        garyNationTitle.isOpaque = true
        garyNationTitle.foreground = Color.white
        garyNationTitle.background = darkGreen
        garyNationTitle.text = "GaryNation Tax Calculator"
        garyNationTitle.verticalAlignment = SwingConstants.CENTER
        garyNationTitle.horizontalAlignment = SwingConstants.CENTER
        garyNationTitle.font = Font (Font.SANS_SERIF, Font.BOLD, 45)

        inputTitle.setBounds(50, 128, 248, 28)
        inputTitle.foreground = darkGreen
        inputTitle.text = "Monthly Income:"
        inputTitle.verticalAlignment = SwingConstants.CENTER
        inputTitle.horizontalAlignment = SwingConstants.CENTER
        inputTitle.font = Font (Font.SANS_SERIF, Font.BOLD, 28)

        inputField.setBounds(306, 122, 343, 40)
        inputField.foreground = darkGreen
        inputField.text = " 0.00"
        inputField.font = Font (Font.SANS_SERIF, Font.BOLD, 18)
        inputField.border = BorderFactory.createLineBorder(darkGreen, 1)

        calculateButton.setBounds(290, 182, 120, 30)
        calculateButton.isOpaque = true
        calculateButton.foreground = Color.white
        calculateButton.background = darkGreen
        calculateButton.text = "CALCULATE"
        calculateButton.verticalAlignment = SwingConstants.CENTER
        calculateButton.horizontalAlignment = SwingConstants.CENTER
        calculateButton.font = Font (Font.SANS_SERIF, Font.BOLD, 13)

        // income tax

        incomeTaxTitle.setBounds(50, 258, 200, 17)
        incomeTaxTitle.foreground = darkGreen
        incomeTaxTitle.text = "Income Tax"
        incomeTaxTitle.font = titleFont

        incomeTaxLabel.setBounds(50, 278, 240, 40)
        incomeTaxLabel.isOpaque = true
        incomeTaxLabel.foreground = darkGreen
        incomeTaxLabel.background = Color.white
        incomeTaxLabel.font = labelFont
        incomeTaxLabel.border = labelBorder

        // net pay after tax

        netATaxTitle.setBounds(50, 335, 200, 17)
        netATaxTitle.foreground = darkGreen
        netATaxTitle.text = "Net Pay After Tax"
        netATaxTitle.font = titleFont

        netATaxLabel.setBounds(50, 355, 240, 40)
        netATaxLabel.isOpaque = true
        netATaxLabel.foreground = darkGreen
        netATaxLabel.background = Color.white
        netATaxLabel.font = labelFont
        netATaxLabel.border = labelBorder

        // total deductions

        totalDedTitle.setBounds(50, 413, 200, 17)
        totalDedTitle.foreground = darkGreen
        totalDedTitle.text = "Total Deductions"
        totalDedTitle.font = titleFont

        totalDedLabel.setBounds(50, 433, 240, 40)
        totalDedLabel.isOpaque = true
        totalDedLabel.foreground = darkGreen
        totalDedLabel.background = Color.white
        totalDedLabel.font = labelFont
        totalDedLabel.border = labelBorder

        // net pay after deduction

        netADedTitle.setBounds(50, 491, 240, 17)
        netADedTitle.foreground = darkGreen
        netADedTitle.text = "Net Pay After Deductions"
        netADedTitle.font = titleFont

        netADedLabel.setBounds(50, 511, 240, 40)
        netADedLabel.isOpaque = true
        netADedLabel.foreground = darkGreen
        netADedLabel.background = Color.white
        netADedLabel.font = labelFont
        netADedLabel.border = labelBorder

        // sss

        sssTitle.setBounds(410, 258, 200, 17)
        sssTitle.foreground = darkGreen
        sssTitle.text = "SSS"
        sssTitle.font = titleFont

        sssLabel.setBounds(410, 278, 240, 40)
        sssLabel.isOpaque = true
        sssLabel.foreground = darkGreen
        sssLabel.background = Color.white
        sssLabel.font = labelFont
        sssLabel.border = labelBorder

        // philHealth

        philHealthTitle.setBounds(410, 335, 200, 17)
        philHealthTitle.foreground = darkGreen
        philHealthTitle.text = "PhilHealth"
        philHealthTitle.font = titleFont

        philHealthLabel.setBounds(410, 355, 240, 40)
        philHealthLabel.isOpaque = true
        philHealthLabel.foreground = darkGreen
        philHealthLabel.background = Color.white
        philHealthLabel.font = labelFont
        philHealthLabel.border = labelBorder

        // pag-IBIG

        pagIBIGTitle.setBounds(410, 413, 200, 17)
        pagIBIGTitle.foreground = darkGreen
        pagIBIGTitle.text = "Pag-IBIG"
        pagIBIGTitle.font = titleFont

        pagIBIGLabel.setBounds(410, 433, 240, 40)
        pagIBIGLabel.isOpaque = true
        pagIBIGLabel.foreground = darkGreen
        pagIBIGLabel.background = Color.white
        pagIBIGLabel.font = labelFont
        pagIBIGLabel.border = labelBorder

        // total contributions

        totalConTitle.setBounds(410, 491, 200, 17)
        totalConTitle.foreground = darkGreen
        totalConTitle.text = "Total Contributions"
        totalConTitle.font = titleFont

        totalConLabel.setBounds(410, 511, 240, 40)
        totalConLabel.isOpaque = true
        totalConLabel.foreground = darkGreen
        totalConLabel.background = Color.white
        totalConLabel.font = labelFont
        totalConLabel.border = labelBorder
    }

    private fun updateLabels() {
        sssLabel.text =         "  ₱ ${decimalFormat.format(sss)}"
        philHealthLabel.text =  "  ₱ ${decimalFormat.format(philHealth)}"
        pagIBIGLabel.text =     "  ₱ ${decimalFormat.format(pagIBIG)}"
        totalConLabel.text =    "  ₱ ${decimalFormat.format(totalContribution)}"
        incomeTaxLabel.text =   "  ₱ ${decimalFormat.format(incomeTax)}"
        netATaxLabel.text =     "  ₱ ${decimalFormat.format(netAfterTax)}"
        totalDedLabel.text =    "  ₱ ${decimalFormat.format(totalDeductions)}"
        netADedLabel.text =     "  ₱ ${decimalFormat.format(netAfterDeductions)}"
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

    private fun inputListener(): ActionListener {
        return ActionListener {
            val decimal = inputField.text.replace(",", "").toDouble()
            try {
                if (decimal < 0)
                    throw Exception("Positive values only!")
                else {
                    monthlyIncome = decimal
                    calculate()
                    updateLabels()
                }

            } catch (e : Exception) {
                JOptionPane.showMessageDialog(
                    panel,
                    "Input positive values only!",
                    "Input Error!",
                    JOptionPane.ERROR_MESSAGE
                )
                println("Positive values only!")
            }
        }
    }
}

fun main() {
    SwingUtilities.invokeLater { // to ensure that the GUI is created and displayed on the event dispatch thread
        val taxApp = TaxApplication()
        taxApp.isVisible = true
    }
}