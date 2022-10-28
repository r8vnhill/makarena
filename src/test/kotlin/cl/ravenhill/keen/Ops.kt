/*
 * "Makarena" (c) by R8V.
 * "Makarena" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 *  work. If not, see <https://creativecommons.org/licenses/by/4.0/>.
 */
package cl.ravenhill.keen

import io.jenetics.prog.op.Op

object Ops {
    @JvmStatic
    fun main(args: Array<String>) {
        val op = Op.of("add", 2) { v: Array<Double> -> v[0] + v[1] }
    }
}