DESCRIPTION = "A C99 SDK for connecting devices to Microsoft Azure IoT services"
HOMEPAGE = "https://github.com/Azure/azure-iot-sdk-c"
LICENSE = "MIT"
SECTION = "libs"
DEPENDS = "openssl curl"
RDEPENDS_${PN} = "openssl libcurl"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=4283671594edec4c13aeb073c219237a"

PR="r0"

ALLOW_EMPTY_${PN} = "1"
SRC_URI = "git://github.com/Azure/azure-iot-sdk-c.git;protocol=http;branch=mrinstall;rev=3de425e8932c3e58a6f10c8bcd6fb7b293a1f28b"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit cmake pkgconfig



# gitsm fetcher does not work.
# Workaround: Initialize git submodules before patch task.
do_gitsubmodules() {
    cd "${WORKDIR}/git"
    git submodule update --init --recursive
    cd "${WORKDIR}/git/c-utility"
    git checkout mrinstall
    cd "${WORKDIR}/git/uamqp"
    git checkout mrinstall
    cd "${WORKDIR}/git/umqtt"
    git checkout mrinstall
}

addtask gitsubmodules after do_unpack before do_patch
