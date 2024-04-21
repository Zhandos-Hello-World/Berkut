import org.gradle.kotlin.dsl.`kotlin-dsl`
import java.net.URI

repositories {
	mavenCentral()
	maven {
		url= URI("https://jitpack.io")
	}
}

plugins {
	`kotlin-dsl`
}