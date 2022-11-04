/*
 * "Makarena" (c) by R8V.
 * "Makarena" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 *  work. If not, see <https://creativecommons.org/licenses/by/4.0/>.
 */

package cl.ravenhill.keen.core

import cl.ravenhill.keen.Builders.engine
import cl.ravenhill.keen.Builders.genotype
import cl.ravenhill.keen.core.chromosomes.BoolChromosome
import cl.ravenhill.keen.signals.EngineConfigurationException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.property.Arb
import io.kotest.property.arbitrary.nonPositiveInt
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll


class EngineSpec : WordSpec({
    lateinit var engine: Engine<Boolean>
    lateinit var genotype: Genotype.GenotypeBuilder<Boolean>

    beforeEach {
        genotype = genotype {
            chromosomes = listOf(BoolChromosome.Builder(20, 0.15))
        }
        engine = engine<Boolean>({ 0.0 }) {
            this.genotype = genotype
        }
    }

    "Engine" When {
        "created" should {
            "start with generation 0" {
                engine.generation shouldBe 0
            }

            "start with an empty population" {
                engine.population.size shouldBe 0
            }

            "start with 0 steady generations" {
                engine.steadyGenerations shouldBe 0
            }
        }
    }

    "Engine builder" When {
        "population size" should {
            "default to 50 if not specified" {
                engine.populationSize shouldBe 50
            }

            "use the given value if it is positive" {
                checkAll(Arb.positiveInt()) { size ->
                    engine = engine({ 0.0 }) {
                        this.genotype = genotype
                        populationSize = size
                    }
                    engine.populationSize shouldBe size
                }
            }

            "throw an exception if it is not positive" {
                checkAll(Arb.nonPositiveInt()) { size ->
                    shouldThrow<EngineConfigurationException> {
                        engine<Boolean>({ 0.0 }) {
                            this.genotype = genotype
                            populationSize = size
                        }
                    }.message shouldContain
                            "Engine configuration error: Population size must be positive"
                }
            }
        }
    }
})