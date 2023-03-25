//import java.text.DecimalFormat
//import java.text.NumberFormat
//import java.util.*
//import kotlin.math.floor
//
//fun main() {
//
//    val decimalFormat = NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat
//    decimalFormat.applyPattern("#,##0.00")
//    var monthlyIncome = 0.0
//
//    try {
//        print("Enter monthly income: ")
//        monthlyIncome = readln().toDouble()
//
//        if (monthlyIncome < 0)
//            throw Exception("Positive values only!")
//
//    } catch (e : Exception) {
//        println("Positive values only!")
//    }
//
//    val sss = sssCompute(monthlyIncome)
//    val philHealth = philhealthCompute(monthlyIncome)
//    val pagIBIG = pagibigCompute(monthlyIncome)
//    val totalContribution = sss + philHealth + pagIBIG
//    val incomeTax = incomeTaxCompute(monthlyIncome - totalContribution)
//    val netAfterTax = (monthlyIncome - incomeTax)
//    val totalDeductions = (incomeTax + totalContribution)
//    val netAfterDeductions = (monthlyIncome - totalDeductions)
//
//    println("""
//        Tax Computation
//        Monthly Income: ${decimalFormat.format(monthlyIncome)}
//
//        Monthly Contribution
//        SSS: ${decimalFormat.format(sss)}
//        PhilHealth: ${decimalFormat.format(philHealth)}
//        Pag-IBIG: ${decimalFormat.format(pagIBIG)}
//        Total Contributions: ${decimalFormat.format(totalContribution)}
//
//        Income Tax: ${decimalFormat.format(incomeTax)}
//        Net Pay After Tax: ${decimalFormat.format(netAfterTax)}
//
//        Total Deductions: ${decimalFormat.format(totalDeductions)}
//        Net Pay After Deductions: ${decimalFormat.format(netAfterDeductions)}
//    """.trimIndent())
//}
//
//fun sssCompute(monthlyIncome : Double) : Double {
//    return when (monthlyIncome) {
//        in 0.0 ..4250.0 -> 180.0
//        in 4251.0 .. 29750.0 -> 180.0 + floor((monthlyIncome - 4250) / 500 + 1) * 22.50
//        else -> 1350.00
//    }
//}
//
//fun philhealthCompute(monthlyIncome : Double) : Double {
//    return when (monthlyIncome) {
//        in 0.0 .. 10000.0 -> 225.0
//        in 10_000.01 .. 79999.99 -> monthlyIncome * 0.0225
//        else -> 1800.0
//    }
//}
//
//fun pagibigCompute(monthlyIncome : Double) : Double {
//    val value: Double = if (monthlyIncome > 5_000) 5000.0 else monthlyIncome
//    return if (value > 1500) value * 0.02 else value * 0.01
//}
//
//fun incomeTaxCompute(deductedIncome: Double) : Double {
//    return when (deductedIncome) {
//        in 0.0 .. 20832.0 -> 0.0
//        in 20833.0 .. 33332.0 -> 0.0 + ((deductedIncome - 20833) * 0.15)
//        in 33333.0 .. 66666.0 -> 1875.00 +  ((deductedIncome - 33333) * 0.20)
//        in 66667.0 .. 166666.0 -> 8541.80 +  ((deductedIncome - 66667) * 0.25)
//        in 166667.0 .. 666666.0 -> 33541.80 +  ((deductedIncome - 166667) * 0.30)
//        else -> 183541.80 + ((deductedIncome - 666667) * 0.35)
//    }
//}