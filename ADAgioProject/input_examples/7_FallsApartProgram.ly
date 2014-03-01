\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=55
\clef treble
\time 4/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.8
\set Staff.midiInstrument = #"acoustic grand"
<< { gis2\mf } \\ { s2 dis'4\mf } \\ { s2 fis'4\mf } \\ { s2. e'4\mf } \\ { s1 } \\ >>
<< { gis'2 } \\ { s2 dis''4 } \\ { s2 fis''4 } \\ { s2. e''4 } \\ { s1 } \\ >>
<< { gis''2 } \\ { s2 a''4 } \\ { s2 c'''4 } \\ { s2. e''4 } \\ { s1 } \\ >>

\tempo 4=50
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. g'4 } \\ { s1 } \\ >>
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. g'4 } \\ { s1 } \\ >>

\tempo 4=55
<< { gis2 } \\ { s2 dis'4 } \\ { s2 fis'4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { gis'2 } \\ { s2 dis''4 } \\ { s2 fis''4 } \\ { s2. e''4 } \\ { s1 } \\ >>
<< { gis''2 } \\ { s2 a''4 } \\ { s2 c'''4 } \\ { s2. e''4 } \\ { s1 } \\ >>

\tempo 4=50
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. gis'4 } \\ { s1 } \\ >>
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'2 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. gis'4 } \\ { s1 } \\ >>

}
\new Staff{
s1
s1
s1

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.6
\set Staff.midiInstrument = #"violin"
<< { e'1\mf } \\ { s2 ais'4\mf } \\ { s2 cis''4\mf } \\ { s2. g'4\mf } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. g'4 } \\ { s1 } \\ >>

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.4
<< { gis1\mf } \\ { s2 dis'4\mf } \\ { s2 fis'4\mf } \\ { s2. e'4\mf } \\ { s1 } \\ >>
<< { gis'1 } \\ { s2 dis''4 } \\ { s2 fis''4 } \\ { s2. e''4 } \\ { s1 } \\ >>
<< { gis''1 } \\ { s2 a''4 } \\ { s2 c'''4 } \\ { s2. e''4 } \\ { s1 } \\ >>

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #0.5
<< { e'1\mf } \\ { s2 ais'4\mf } \\ { s2 cis''4\mf } \\ { s2. gis'4\mf } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. e'4 } \\ { s1 } \\ >>
<< { e'1 } \\ { s2 ais'4 } \\ { s2 cis''4 } \\ { s2. gis'4 } \\ { s1 } \\ >>

}
>> 
\layout{ }
\midi {
\context {
\Score 
tempoWholesPerMinute = #(ly:make-moment 72 2)
}
}
}