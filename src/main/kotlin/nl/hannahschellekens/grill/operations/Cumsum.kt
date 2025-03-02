package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun Matrix<Int>.cumsumInt(): Matrix<Int> = runningFoldInt(0) { a, b -> a + b }

fun Matrix<Double>.cumsum(): Matrix<Double> = runningFold(0.0) { a, b -> a + b }

fun Matrix<Int>.cumsumRowsInt(): Matrix<Int> {
    return runningFoldRowsInt(intNullRow(width)) { acc, row -> acc.addIntModify(row) }
}

fun Matrix<Double>.cumsumRows(): Matrix<Double> {
    return runningFoldRows(doubleNullRow(width)) { acc, row -> acc.addModify(row) }
}

fun Matrix<Int>.cumsumColumnsInt(): Matrix<Int> {
    return runningFoldColumnsInt(intNullColumn(height)) { acc, col -> acc.addIntModify(col) }
}

fun Matrix<Double>.cumsumColumns(): Matrix<Double> {
    return runningFoldColumns(doubleNullColumn(height)) { acc, col -> acc.addModify(col) }
}