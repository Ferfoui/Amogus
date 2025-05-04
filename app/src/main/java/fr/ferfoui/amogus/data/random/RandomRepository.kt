package fr.ferfoui.amogus.data.random

class RandomRepository {

    private var _intervalMax: UInt = 0u
    private var _count: UInt = 0u
    private var _duplicates: UInt = 1u

    var intervalMax: UInt
        get() = _intervalMax
        set(value) {
            _intervalMax = value
            count = _count
        }

    var count: UInt
        get() = _count
        set(value) {
            val allowedCount = _intervalMax * _duplicates
            if (value > allowedCount) {
                _count = allowedCount
            }
            _count = value
        }

    var duplicates: UInt
        get() = _duplicates
        set(value) {
            _duplicates = value
            count = _count
        }

    fun generateRandomNumbers(): List<Int> {
        return generateRandomNumbersWithDuplicates(_intervalMax, _count, _duplicates)
    }

}