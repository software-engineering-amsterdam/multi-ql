Pod::Spec.new do |s|
  s.name = 'SwiftParsec'
  s.version = '1.0'
  s.license = 'LICENSE'
  s.summary = 'SwiftParsec is a Swift port of the Parsec parser combinator library.'
  s.homepage = 'https://github.com/davedufresne/SwiftParsec'
  s.authors = { 'David Dufresne' => 'https://github.com/davedufresne' }
  s.source = { :git => 'https://github.com/davedufresne/SwiftParsec.git', :tag => s.version }

  s.ios.deployment_target = '8.0'
  s.osx.deployment_target = '10.9'
  s.tvos.deployment_target = '9.0'
  s.watchos.deployment_target = '2.0'

  s.source_files = 'SwiftParsec/*.swift'
end
