/*
 * "Makarena" (c) by R8V.
 * "Makarena" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 *  work. If not, see <https://creativecommons.org/licenses/by/4.0/>.
 */

package cl.ravenhill

import cl.ravenhill.keen.Builders.engine
import cl.ravenhill.keen.Builders.genotype
import cl.ravenhill.keen.core.Genotype
import cl.ravenhill.keen.core.chromosomes.BoolChromosome
import cl.ravenhill.keen.operators.alterers.Mutator
import cl.ravenhill.keen.operators.alterers.SinglePointCrossover
import cl.ravenhill.keen.operators.selector.RouletteWheelSelector
import io.jenetics.BitChromosome
import io.jenetics.BitGene
import io.jenetics.Genotype as JGenotype


/**
 * Documentation
 */
fun jCount(genotype: JGenotype<BitGene>) =
    (genotype.chromosome().`as`(BitChromosome::class.java).bitCount())

fun count(genotype: Genotype<Boolean>): Double =
    genotype.chromosomes[0].genes.count { it.dna }.toDouble()

fun main() {
    val engine = engine(::count) {
        genotype = genotype {
            chromosomes = listOf(BoolChromosome.Builder(20, 0.15))
        }
        populationSize = 500
        survivors = (populationSize * 0.2).toInt()
        survivorSelector = RouletteWheelSelector()
        alterers = listOf(Mutator(0.55), SinglePointCrossover(0.06))
    }
    engine.evolve()
    println("${engine.fittest} -> ${engine.fittest?.fitness}")
}
