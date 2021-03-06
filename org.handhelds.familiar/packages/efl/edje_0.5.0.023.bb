DESCRIPTION = "Edje is a complex graphical design & layout library."
# can also install vim data files
DEPENDS = "virtual/evas virtual/ecore eet embryo edje-native virtual/imlib2"
LICENSE = "MIT"
PR = "r6"

## package.bbclass kills packages when there are duplicates, this means
##  that efl.bbclass can't have PACKAGES += and so the FILES for previous
##  packages take precedence over -utils
#PACKAGES = "edje-utils"
#RPROVIDES_edje-utils += "libedje-utils"
#FILES_edje-utils = "${bindir}/edje ${bindir}/edje_* ${datadir}/edje/data/template ${datadir}/edje/include"
#RDEPENDS_edje-utils += "libembryo-utils cpp cpp-symlinks gcc gcc-symlinks"

inherit efl

FILES_${PN}-examples = "${datadir}/edje/data/images ${datadir}/edje/data/src ${datadir}/edje/data/test ${datadir}/edje/data/*.sh"

FILES_${SRCNAME}-themes = ""

SRC_URI += "cvs://anonymous@thinktux.net/root;module=e17/libs/edje/m4;date=20060101"

LEAD_SONAME = "libedje.so"

do_configure_prepend() {
	install -d "${S}/m4"
	install "${WORKDIR}/m4/"*.m4 "${S}/m4"
	aclocal -I m4
}
