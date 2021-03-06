DESCRIPTION = "Python GTK+ Bindings"
SECTION = "devel/python"
PRIORITY = "optional"
MAINTAINER = "Michael 'Mickey' Lauer <mickey@Vanille.de>"
DEPENDS = "gtk+ libglade"
RDEPENDS = "python-shell"
SRCNAME = "pygtk"
LICENSE = "LGPL"
PR = "r4"

SRC_URI = "ftp://ftp.gnome.org/pub/gnome/sources/pygtk/2.6/${SRCNAME}-${PV}.tar.bz2 \
           file://obey-path.patch;patch=1 \
           file://acinclude.m4"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit autotools pkgconfig distutils-base

PACKAGES = "${PN}-demos ${PN}-dev ${PN}-doc ${PN}"
FILES_${PN}-demos = "${bindir}/*demo* ${libdir}/pygtk/2.0/*demo*"
FILES_${PN}-dev += "${bindir}/*codegen* ${datadir}/pygtk/2.0/codegen ${datadir}/pygtk/2.0/defs"
FILES_${PN} = "${libdir}/python*"

do_configure_prepend() {
	install -m 0644 ${WORKDIR}/acinclude.m4 ${S}/
}

do_stage() {
	autotools_stage_includes
	install -m 0755 codegen/pygtk-codegen-2.0 ${STAGING_BINDIR}/
}

