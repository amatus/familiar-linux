include coreutils_${PV}.bb
inherit native

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/coreutils-${PV}"
S = "${WORKDIR}/coreutils-${PV}"
