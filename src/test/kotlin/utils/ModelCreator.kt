package utils

import com.gov.uk.model.User
import utils.SequenceUtil.nextDouble
import utils.SequenceUtil.nextLong
import utils.SequenceUtil.nextString

object ModelCreator {

    fun aUser(
        latitude: Double = nextDouble(),
        longitude: Double = nextDouble()
    ) = User(
        id = nextLong(),
        firstName = nextString(),
        lastName = nextString(),
        email = nextString(),
        ipAddress = nextString(),
        latitude = latitude,
        longitude = longitude
    )

}
