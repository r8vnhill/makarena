/*
 * "Makarena" (c) by R8V.
 * "Makarena" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 *  work. If not, see <https://creativecommons.org/licenses/by/4.0/>.
 */

package cl.ravenhill.keen.operators.crossover

import cl.ravenhill.keen.core.Genotype
import cl.ravenhill.keen.core.KeenCore
import cl.ravenhill.keen.operators.Alterer
import kotlin.random.asKotlinRandom


abstract class AbstractCrossover<DNA>(override val probability: Double) : Alterer<DNA> {
    override fun invoke(population: List<Genotype<DNA>>): List<Genotype<DNA>> {
        return population.map {
            val mate = population.random(KeenCore.generator.asKotlinRandom())
            crossover(it to mate)
        }
    }

    abstract fun crossover(mates: Pair<Genotype<DNA>, Genotype<DNA>>): Genotype<DNA>
}