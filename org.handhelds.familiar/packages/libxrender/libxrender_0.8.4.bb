SECTION = "libs"
DEPENDS = "renderext x11"
DESCRIPTION = "X Render extension library."
LICENSE = "BSD"
PR = "r2"

SRC_URI = "${XLIBS_MIRROR}/libXrender-${PV}.tar.bz2 \
	   file://autofoo.patch;patch=1"
S = "${WORKDIR}/libXrender-${PV}"

inherit autotools pkgconfig 

do_stage() {
	autotools_stage_all
}
