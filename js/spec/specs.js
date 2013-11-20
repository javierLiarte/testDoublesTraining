var should = chai.should();

describe("the user service", function(){
  
  var service, repo; 
  beforeEach(function(){
      repo = userRepository();
      service = userService(repo);
  });

  it("retrieves users using hardcoded stub", function(){
      repo.retrieveUsers = function(){
          return [{email: 'test@test.com'}];
      }

      var foundUser = service.findBy("test@test.com");

      foundUser.hasEmail("test@test.com").should.be.ok;
  });

  it("retrieves users using Jasmine stub", function(){
      spyOn(repo, "retrieveUsers").andCallFake(function(){
          return [{email: 'test@test.com'}];
      });

      var foundUser = service.findBy("test@test.com");

      foundUser.hasEmail("test@test.com").should.be.ok;
   });

  it("stores a user using hardcoded spy", function(){
      repo.store = function(rawUser){
          this.storedUser = rawUser;
      };

      service.add(new User({email: 'test@test.com'}));

      repo.storedUser.email.should.equal('test@test.com');
  });

  it("stores a user using Jasmine spy ", function(){
      spyOn(repo, "store").andCallFake(function(rawUser){
          rawUser.email.should.equal('test@test.com');
      });

      service.add(new User({email: 'test@test.com'}));

      expect(repo.store).toHaveBeenCalled();
  });

  it("stores a user using sinon spy ", function(){
      sinon.spy(repo, "store");

      service.add(new User({email: 'test@test.com'}));

      repo.store.called.should.be.ok;
      repo.store.getCall(0).args[0].email.should.equal('test@test.com');
  });

});

