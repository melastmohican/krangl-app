import krangl.*
import org.apache.commons.csv.CSVFormat
import java.io.*

fun main(args: Array<String>) {
	println(System.getProperty("user.dir"))
	val hA: Array<String> = arrayOf("mutation_id", "unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr",
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
			"other_complex_with_phenotype", "other_complex_without_phenotype")
	println(hA.size)
	val hB: Array<String> = arrayOf<String>("mutation_id",
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
			"complex_homozygous_without_phenotype", "other_complex_with_phenotype", "other_complex_without_phenotype")
	println(hB.size)

	val a: DataFrame = DataFrame.fromCSV(File("mutation_property.txt"), format = CSVFormat.TDF.withHeader(*hA))
	a.glimpse()
	val b: DataFrame = DataFrame.fromCSV(File("mutation_property_pwcb_test.txt"), format = CSVFormat.newFormat('|').withHeader(*hB))
	b.glimpse()

	val ga = a.count(*hA)
	val gb = b.count(*hB)
	println(ga.rows.toList())
	println(ga.rows.toList().size)
	println(gb.rows.toList())
	println(gb.rows.toList().size)
	println(ga.rows.toList() == gb.rows.toList())

}