package com.example.toozhackathon

import tooz.bto.toozifier.error.ErrorCause
import tooz.bto.toozifier.registration.RegistrationListener

class EmptyRegistrationListener : RegistrationListener {
    override fun onDeregisterFailure(errorCause: ErrorCause) {
    }

    override fun onDeregisterSuccess() {
    }

    override fun onRegisterFailure(errorCause: ErrorCause) {
    }

    override fun onRegisterSuccess() {
    }
}