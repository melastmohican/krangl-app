import krangl.*
import org.apache.commons.csv.CSVFormat
import java.io.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
	println(System.getProperty("user.dir"))
	val ta = measureTimeMillis {
		val hA: Array<String> = arrayOf("mutation_id", "unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr",
				"disease_segregates_with_mutation", "finding_id", "functional_study_protein_has_property", "gain_of_function",
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
		val hB: Array<String> = arrayOf<String>("mutation_id", "unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr", "disease_segregates_with_mutation",
				"finding_id", "functional_study_protein_has_property", "gain_of_function", "mutation_property", "acquisition_source_id",
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

		val fA: Array<String> = arrayOf<String>("mutation_id", "unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr",
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
		val fB: Array<String> = arrayOf<String>("mutation_id", "unspecified_with_phenotype_chr", "unspecified_without_phenotype_chr", "disease_segregates_with_mutation",
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
		val a: DataFrame = DataFrame.fromCSV(File("mutation_property.txt"), format = CSVFormat.TDF.withHeader(*hA))
		a.glimpse()
		val b: DataFrame = DataFrame.fromCSV(File("mutation_property_pwcb_test.txt"), format = CSVFormat.newFormat('|').withHeader(*hB))
		b.glimpse()

		val ga = a.count(*fA)
		val gb = b.count(*fB)
		println(ga.rows.toList())
		println(ga.rows.toList().size)
		println(gb.rows.toList())
		println(gb.rows.toList().size)
		println(ga.rows.toList() == gb.rows.toList())
	}
	println("ta = ${ta / 1000} sec")
	if (File("ING_MUTATION_PROPERTIES.txt.gz").exists()) {
		val taa = measureTimeMillis {
			println("AA")
			val aa: DataFrame = DataFrame.fromCSV(File("ING_MUTATION_PROPERTIES.txt.gz"), format = CSVFormat.newFormat('|').withHeader())
			aa.glimpse()
			println("BB")
			val bb: DataFrame = DataFrame.fromCSV(File("mutationIdToFinding.txt.gz"), format = CSVFormat.TDF.withHeader("mutationId", "findingId"))
			bb.glimpse()
			println("Count aa")
			val gaa = aa.count("?MutationId", "?FindingID")
			println("Count bb")
			val gbb = bb.count("mutationId", "findingId")
			//println(gaa.rows.toList())
			println(gaa.rows.toList().size)
			//println(gbb.rows.toList())
			println(gbb.rows.toList().size)
			println(gaa.rows.toList() == gbb.rows.toList())
		}
		println("taa = ${taa / 1000} sec")
	}
}