import krangl.*
import org.apache.commons.csv.CSVFormat
import java.io.*

fun main(args: Array<String>) {
    println(System.getProperty("user.dir"))
/*
    val df: DataFrame = DataFrame.fromCSV(File("bel_epoio_data.txt"), format = CSVFormat.TDF)

    // Print rows
    df                              // with default printing options
    df.print(colNames = false)      // with custom  printing options

    // Print structure
    df.glimpse()
*/

    /*
    DataFrame with 1343539 observations
    ?FINDING_ID     : [Str] , [ING:6gwzo, ING:1c3a9, ING:19nkf, ING:2z4gt, ING:5f4ni, ING:54hko, ING:19nkf, ING:jvu0]
    ?EFFECTOR_UID   : [Int] , [979601917, 1053501967, 1689155303, 1271537187, 1699007625, 874569169, 2095457897, 1013213491]
    ?EFFECTOR_RELATIONSHIP  : [Str] , [increases, increases, causesNoChange, increases, decreases, increases, causesNoChange, increases]
    ?EFFECTOR_DIRECTNESS    : [Str] , [unknown, unknown, unknown, direct, unknown, unknown, unknown, unknown]
    ?OBJECT_UID     : [Int] , [1883705205, 243248353, 1051024117, 1440120639, 1873766775, 1340348709, 1051024117, 611322903]
    */
/*
    // Resort with arrange
    val df1: DataFrame = df.arrange("?FINDING_ID")
    df1.print(colNames = false)
    // and add secondary sorting attributes as varargs
    val df2: DataFrame = df.arrange("?FINDING_ID", "?EFFECTOR_UID")
    df2.print(colNames = false)

    // Subset columns with select
    val df3: DataFrame = df.select("?FINDING_ID", "?EFFECTOR_UID")    // positive selection
    df3.print(colNames = false)
    val df4: DataFrame = df.select(-"EFFECTOR_UID", -"?OBJECT_UID")  // negative selection
    df4.print(colNames = false)
    val df5: DataFrame = df.select({ endsWith("NoChange") })    // selector mini-language
    df5.print(colNames = false)

    // Subset rows with filter
    val df6: DataFrame = df.filter { it["?FINDING_ID"] eq "ING:6gwzo" }
    df6.print(colNames = false)
    val df7: DataFrame = df.filter({ it["?EFFECTOR_RELATIONSHIP"].asStrings().map { it!!.endsWith("NoChange") }.toBooleanArray() })
    df7.print(colNames = false)
*/

    val a: DataFrame = DataFrame.fromCSV(File("mutation_property125933259.txt"), format = CSVFormat.TDF.withHeader("mutation_id", "unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr",
						"disease_segregates_with_mutation", "functional_study_protein_has_property", "gain_of_function",
						"mutation_property", "acquisition_source_id", "compound_het_with_phenotype",
						"compound_het_without_phenotype", "het_with_phenotype", "het_without_phenotype",
						"num_other_complex_with_phenotype", "num_other_complex_without_phenotype", "num_total_het_with_phenotype",
						"num_total_het_without_phenotype", "unspecified_with_phenotype", "unspecified_without_phenotype",
						"patients_from_single_family", "affected_family_member_lacks_mutation", "primary_reference",
						"hemizygous_with_phenotype", "hemizygous_without_phenotype", "mut_in_other_gene_with_phenotype",
						"mut_in_other_gene_without_phenotype", "total_het_with_phenotype", "total_het_without_phenotype",
						"finding_type", "patients_with_phenotype", "patients_without_phenotype", "denovo", "clinical_significance",
						"complex_homozygous_with_phenotype", "complex_homozygous_without_phenotype",
						"other_complex_with_phenotype", "other_complex_without_phenotype"))
    //a                              // with default printing options
    //a.print(colNames = false)      // with custom  printing options
    //a.glimpse()

    val b: DataFrame = DataFrame.fromCSV(File("mutation_property_pwcb_test125933259.txt"), format = CSVFormat.newFormat('|').withHeader("mutation_id",
						"unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr", "disease_segregates_with_mutation",
						"functional_study_protein_has_property", "gain_of_function", "mutation_property", "acquisition_source_id",
						"compound_het_with_phenotype", "compound_het_without_phenotype", "het_with_phenotype",
						"het_without_phenotype", "num_other_complex_with_phenotype", "num_other_complex_without_phenotype",
						"num_total_het_with_phenotype", "num_total_het_without_phenotype", "unspecified_with_phenotype",
						"unspecified_without_phenotype", "patients_from_single_family", "affected_family_member_lacks_mutation",
						"primary_reference", "hemizygous_with_phenotype", "hemizygous_without_phenotype",
						"mut_in_other_gene_with_phenotype", "mut_in_other_gene_without_phenotype", "total_het_with_phenotype",
						"total_het_without_phenotype", "finding_type", "patients_with_phenotype", "patients_without_phenotype",
						"denovo", "clinical_significance", "complex_homozygous_with_phenotype",
						"complex_homozygous_without_phenotype", "other_complex_with_phenotype", "other_complex_without_phenotype"))
    //b                              // with default printing options
    //b.print(colNames = false)      // with custom  printing options
    //b.glimpse()

    val ca = a.rows
        .groupBy { listOf(it["mutation_id"],it["acquisition_source_id"])}
        .map { listOf(it.key[0],it.key[1], it.value.count()) }
        .sortedWith(compareBy( { it[0].toString() }, { it[1].toString() } ))
    println(ca)
    val cb = b.rows
        .groupBy { listOf(it["mutation_id"],it["acquisition_source_id"])}
        .map { listOf(it.key[0],it.key[1], it.value.count()) }
        .sortedWith(compareBy( { it[0].toString() }, { it[1].toString() } ))
    println(cb)

    /*
    val cab = ca + cb
    println(cab.size)
    val cit = ca.intersect(cb)
    println(cit.size)
    val csd = cab - cit
    println(csd.size)
    println("A Δ B = $csd")
    */
    println (ca == cb)
}